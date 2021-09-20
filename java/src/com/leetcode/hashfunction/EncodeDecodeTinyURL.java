package com.leetcode.hashfunction;

import java.util.*;

/**
 * 535. Encode and Decode TinyURL
 * Medium
 *
 * 1000
 *
 * 1998
 *
 * Add to List
 *
 * Share
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 *
 *
 * Example 1:
 *
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 *
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after deconding it.
 *
 *
 * Constraints:
 *
 * 1 <= url.length <= 104
 * url is guranteed to be a valid URL.
 */
public class EncodeDecodeTinyURL {

    public static void main(String[] args) {
        EncodeDecodeTinyURL codec = new EncodeDecodeTinyURL();
        codec.decode(codec.encode("http://xyz.com/createEmployee"));
    }

    // Short to Long URL Map
    Map<String, String> mapUrl = new HashMap<>();
    Map<String, String> mapUrlRev = new HashMap<>();
    int counter = 0;
    char[] CHAR_NUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {

        if (mapUrl.containsKey(mapUrlRev)) {
            return mapUrlRev.get(longUrl);
        }

        String shortURL = convertIntToHashCode(++counter);
        mapUrl.put(shortURL, longUrl);
        mapUrlRev.put(longUrl, shortURL);

        return shortURL;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return  mapUrl.get(shortUrl);
    }

    // Convert int to hascode
    public String convertIntToHashCode(int num) {

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int remainder = num % CHAR_NUM.length;
            sb.append(CHAR_NUM[remainder]);
            num = num / CHAR_NUM.length;
        }
        return sb.reverse().toString();
    }
}

// Your Codec object will be instantiated and called as such:
// EncodeDecodeTinyURL codec = new EncodeDecodeTinyURL();
// codec.decode(codec.encode(url));
