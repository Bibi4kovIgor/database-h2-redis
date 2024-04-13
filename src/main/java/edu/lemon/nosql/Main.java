package edu.lemon.nosql;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    try (JedisPool pool = new JedisPool("localhost", 6379);
         Jedis jedis = pool.getResource();
    ){
      jedis.set("key", "value");
      System.out.println(jedis.get("key"));

      Map<String, String> data = new HashMap<>();
      data.put("name", "Ihor");
      data.put("surname", "Bibichkov");
      data.put("company", "someCompany");
      data.put("age", "32");
      jedis.hset("user-session:123", data);
      System.out.println(jedis.hgetAll("user-session:123"));

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }
}
