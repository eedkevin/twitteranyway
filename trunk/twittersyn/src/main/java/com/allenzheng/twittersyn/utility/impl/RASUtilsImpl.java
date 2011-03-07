/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.allenzheng.twittersyn.utility.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import com.allenzheng.twittersyn.utility.RSAUtils;

/**
 * Project Name:twittersyn
 * @author Allen Cheng 
 * Email:xudongzheng.boc@gmail.com
 * @version 2011-3-6
 * 
 */
public class RASUtilsImpl implements RSAUtils {
	
	private final static String KEY_FILE_PATH = "/";
	
	private final static String PUBLIC_KEY = "PublicKey";
	private final static String PRIVATE_KEY = "PrivateKey";


	public String encryptText(String plaintext) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	
	public String decryptText(String ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void iniKeyPair() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
		
		File publicKeyFile = new File(KEY_FILE_PATH+PUBLIC_KEY);
		File privateKeyFile = new File(KEY_FILE_PATH+PRIVATE_KEY);
		
		if(!publicKeyFile.exists() && !privateKeyFile.exists()){
			
			SecureRandom sr = new SecureRandom();
			KeyPairGenerator keypg = KeyPairGenerator.getInstance("RAS");
			keypg.initialize(1024, sr);
			KeyPair keyPair = keypg.generateKeyPair();
			
			Key publicKey = keyPair.getPublic();
			Key privateKey = keyPair.getPrivate();
			
			ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
			ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
			
			oos1.writeObject(publicKey);
			oos2.writeObject(privateKey);
			
			oos1.close();
			oos2.close();
		}else{
			
		}
			
		
	}
	
	private Key loadKey(String keyType) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException{
		
		ObjectInputStream inputStream;
		
		File keyFile = new File(KEY_FILE_PATH+keyType);
		if(keyFile.exists()){
			inputStream = new ObjectInputStream(new FileInputStream(keyFile));
			Key key =(Key) inputStream.readObject(); 
			inputStream.close();
			return key;
		}else{
			iniKeyPair();
			return this.loadKey(keyType);
			
		}
		
		
	}
	
	

}
