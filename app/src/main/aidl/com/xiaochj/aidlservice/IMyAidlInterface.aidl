// IMyAidlInterface.aidl
package com.xiaochj.aidlservice;

// Declare any non-default types here with import statements

//1、clent进程的aidl文件，注意，要和远端进程的包名，类名，方法名一致，Build-clean-rebuild，在build-generated-source-aidl-debug下面生成java文件（转AidlService.java）
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int a,int b);
}
