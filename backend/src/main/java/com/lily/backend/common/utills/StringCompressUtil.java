package com.lily.backend.common.utills;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;


@Slf4j
public class StringCompressUtil {
  private static final Base64 base64 = new Base64();

  public static String compress(String input) throws IOException {
    if (input == null) {
      return null;
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    GZIPOutputStream gzip = new GZIPOutputStream(baos);
    gzip.write(input.getBytes(StandardCharsets.UTF_8));
    gzip.close();
    return base64.encodeToString(baos.toByteArray());
  }

  public static String decompress(String input) {
    if (input == null) {
      return null;
    }

    try {
      byte[] base64Bytes = base64.decode(input);
      GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(base64Bytes));
      BufferedReader bf = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8));

      StringBuffer decompressBuffer = new StringBuffer();
      String line;
      while ((line = bf.readLine()) != null) {
        decompressBuffer.append(line);
      }
      bf.close();
      gis.close();
      return decompressBuffer.toString();
    } catch (Exception e) {
      log.error("content decompress was fail");
      return "";
    }
  }

}
