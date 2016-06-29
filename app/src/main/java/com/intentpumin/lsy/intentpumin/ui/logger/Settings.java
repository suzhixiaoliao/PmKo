package com.intentpumin.lsy.intentpumin.ui.logger;

/**
 * @author Orhan Obut
 */
public final class Settings {

  private int methodCount = 2;
  private boolean showThreadInfo = true;
  private int methodOffset = 0;
  private boolean smartTag = false;

  /**
   * Determines how logs will printed
   */
  private LogLevel logLevel = LogLevel.FULL;

  public com.intentpumin.lsy.intentpumin.ui.logger.Settings hideThreadInfo() {
    showThreadInfo = false;
    return this;
  }

  public com.intentpumin.lsy.intentpumin.ui.logger.Settings setMethodCount(int methodCount) {
    if (methodCount < 0) {
      methodCount = 0;
    }
    this.methodCount = methodCount;
    return this;
  }

  public com.intentpumin.lsy.intentpumin.ui.logger.Settings setLogLevel(com.intentpumin.lsy.intentpumin.ui.logger.LogLevel logLevel) {
    this.logLevel = logLevel;
    return this;
  }

  public com.intentpumin.lsy.intentpumin.ui.logger.Settings setMethodOffset(int offset) {
    this.methodOffset = offset;
    return this;
  }

  public com.intentpumin.lsy.intentpumin.ui.logger.Settings smartTag(boolean smartTag) {
    this.smartTag = smartTag;
    return this;
  }

  public int getMethodCount() {
    return methodCount;
  }

  public boolean isShowThreadInfo() {
    return showThreadInfo;
  }

  public com.intentpumin.lsy.intentpumin.ui.logger.LogLevel getLogLevel() {
    return logLevel;
  }

  public int getMethodOffset() {
    return methodOffset;
  }

  public boolean isSmartTag() {
    return smartTag;
  }
}
