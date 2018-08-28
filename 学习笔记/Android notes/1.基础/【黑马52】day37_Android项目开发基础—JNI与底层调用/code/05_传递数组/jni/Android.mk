LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := arr
LOCAL_SRC_FILES := arr.c

include $(BUILD_SHARED_LIBRARY)
