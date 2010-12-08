﻿/*
 *  Copyright (c) 2009, Steven Wang
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *  twitterSina at http://twitterSina.appspot.com
 *  twitterSina code at http://twitterSina.googlecode.com
 * 	
 */
package com.allenzheng.twittersyn;

import java.net.*;
import java.io.*; 

import com.allenzheng.twittersyn.common.*;

import twitter4j.http.BASE64Encoder;;

/**
 * 利用API对嘀咕进行相关操作
 * @author Steven Wang <http://steven-wang.appspot.com>
 */
public class DiguAPI 
{
	/**
	* 验证嘀咕账号
	* @param userName，嘀咕账号
	* @param userPwd，嘀咕密码
	* @return，是否验证成功
	*/
	public boolean loginDigu(String userName, String userPwd)
	{
		HttpURLConnection httpURLConnection = null;
		String responseStr = null;
		String auth = userName + ":" + userPwd;
		try
		{
			URLConnection con = new URL("http://api.minicloud.com.cn/account/verify.json").openConnection();
			if(con != null)
			{
				httpURLConnection = (HttpURLConnection)con;
			}
			else
			{
				return false;
			}
			httpURLConnection.setRequestMethod("get");
			httpURLConnection.setRequestProperty("Authorization","Basic " + new BASE64Encoder().encode(auth.getBytes("utf-8")));
			httpURLConnection.setDoOutput(true);

			BufferedReader httpBufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));   
			responseStr = HttpHelp.readBufferedContent(httpBufferedReader);
			
			if (responseStr != null && responseStr.length() > 0 && responseStr.indexOf("error") == -1)
			{
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
		finally
		{
			try
			{
				httpURLConnection.disconnect();
			}
			catch(Exception e)
			{
				return false;
			}
		}
	}
	
	/**
	* 更新嘀咕状态
	* @param userName，嘀咕账号
	* @param userPwd，嘀咕密码
	* @param publishContent，发布的内容
	* @return，返回发布是否成功
	*/
	public boolean publishDigu(String userName, String userPwd, String publishContent)
	{
		String auth = userName + ":" + userPwd;
		String postData = null;
		try 
		{
			postData = "content=" + URLEncoder.encode(publishContent,"utf-8");
		}
		catch (UnsupportedEncodingException e1)
		{
		}
		HttpURLConnection httpURLConnection = null;
		OutputStream httpOutputStream = null;
		String responseStr = null;
		try
		{
			URLConnection con = new URL("http://api.minicloud.com.cn/statuses/update.xml").openConnection();
			if(con != null)
			{
				httpURLConnection = (HttpURLConnection)con;
			}
			else
			{
				return false;
			}
			httpURLConnection.setRequestMethod("post");
			httpURLConnection.setRequestProperty("Authorization","Basic " + new BASE64Encoder().encode(auth.getBytes("utf-8")));
			httpURLConnection.setDoOutput(true);
			httpOutputStream = httpURLConnection.getOutputStream();
			httpOutputStream.write(postData.getBytes("utf-8"));

			BufferedReader httpBufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));   
			responseStr = HttpHelp.readBufferedContent(httpBufferedReader);
			
			if (responseStr != null && responseStr.length() > 0 && responseStr.indexOf("error") == -1)
			{
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
		finally
		{
			try
			{
				httpOutputStream.close();
				httpURLConnection.disconnect();
			}
			catch(Exception e)
			{
				return false;
			}
		}
	}
}
