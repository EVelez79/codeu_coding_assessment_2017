// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.io.IOException;
import java.util.*;

final class MyJSONParser implements JSONParser {

  @Override
  public JSON parse(String in) throws IOException {
	  String newIn = null;
	  String key;
	  String value;
	  HashMap<String, Object> map = new HashMap<String, Object>();

	  in = in.trim();

	// checks for valid input
	  if(in.length() < 1) {
		  throw new IOException("Not valid");
		// checks for quotations in JSON-lite, if none, it is considered an empty object.
	  } else if (in.indexOf('"') == -1) {
		  return new MyJSON(map);
	  }

	// checks for {} and makes new String from substring between {}
	  if(in.charAt(0) == '{' && in.charAt(in.length()-1) == '}') {
		  newIn = in.substring(1, in.length()-1).trim();
	  }

	// makes key value from [0] to first colon
	  key = newIn.substring(0, newIn.indexOf(":")).trim(); // makes key value from [0] to first colon
	  value = newIn.substring(newIn.indexOf(":")+1, newIn.length()).trim();

	// checks if "" are present in key object and takes substring between ""
	  if(key.charAt(0) == '"' && key.charAt(key.length()-1) == '"') {
		  key = key.substring(1, key.length()-1).trim();
	  }

	// checks if "" are present in value object and takes substring between ""
	  if(value.charAt(0) == '"' && value.charAt(value.length()-1) == '"') {
		  value = value.substring(1, value.length()-1).trim();
	  } else if(value.charAt(0) == '{' && value.charAt(value.length()-1)=='}') {
		  value = value.substring(1, value.length()-1).trim();
	  }

	  map.put(key, value);

    return new MyJSON(map);
  }
}
