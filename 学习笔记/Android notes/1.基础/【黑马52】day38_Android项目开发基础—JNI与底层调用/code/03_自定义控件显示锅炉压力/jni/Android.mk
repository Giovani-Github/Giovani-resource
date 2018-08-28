LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := monitor
LOCAL_SRC_FILES := monitor.c

include $(BUILD_SHARED_LIBRARY)
