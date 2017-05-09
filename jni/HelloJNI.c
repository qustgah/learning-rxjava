#include<jni.h>
#include <stdio.h>
#include "com_jni_HelloJNI.h"

JNIEXPORT void JNICALL Java_com_jni_HelloJNI_modifyField(JNIEnv *env, jobject thisObj) {


    jclass thisClass = (*env) -> GetObjectClass(env,thisObj);
    jfieldID fieldNum = (*env) -> GetFieldID(env,thisClass,"number","I");
    if(fieldNum == NULL) return;
    jint number = (*env) -> GetIntField(env,thisObj,fieldNum);
    printf("C number 属性值：%d\n",number);

    number = 99;
    (*env) -> SetIntField(env,thisObj,fieldNum,number);

    jfieldID fieldMessage = (*env) -> GetFieldID(env,thisClass,"message","Ljava/lang/String;");
    if(fieldMessage == NULL) return;

    jstring message = (*env) -> GetObjectField(env,thisObj,fieldMessage);

    const char *cStr = (*env) -> GetStringUTFChars(env,message,NULL);
    if(cStr == NULL) return;

    printf("C message : %s \n" ,cStr);
    (*env) -> ReleaseStringUTFChars(env,message,cStr);

    message = (*env)->NewStringUTF(env,"hello from C");
    if(message == NULL) return;

    (*env)-> SetObjectField(env,thisObj,fieldMessage,message);

}
JNIEXPORT jobject JNICALL Java_com_jni_HelloJNI_getInstance
  (JNIEnv *env, jclass thisClass ){

  jclass cls = (*env)-> FindClass(env,"com/jni/HelloJNI");
  jmethodID method = (*env) -> GetMethodID(env,cls,"<init>","(Ljava/lang/String;)V");
  if(method == NULL) return NULL;
  jstring name = (*env) -> NewStringUTF(env,"Ahui");
  jobject newObj = (*env) -> NewObject(env,cls,method,name);

  return newObj;





}




































