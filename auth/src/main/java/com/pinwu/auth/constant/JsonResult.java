package com.pinwu.auth.constant;

import lombok.Data;

/**
 * @author Bin Jia
 * @date 2020/9/6 1:37
 */
@Data
public class JsonResult<T> {
  private String msg;

  private boolean success;

  private T detail;

}
