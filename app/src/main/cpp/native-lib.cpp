#include <jni.h>
#include <string>
#include <map>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_id_ac_ui_cs_mobileprogramming_yusuftriardho_twofastfingers_viewmodels_PlayViewModel_decryptWord(
        JNIEnv *env,
        jobject,
        jstring msg) {

    jboolean isCopy;
    const char *convertedValue = (env)->GetStringUTFChars(msg, &isCopy);
    string ret = string(convertedValue);
    string key = "3waBdSKzQns5OJ2fDcIL4ePUXHxyG18MvgqhrTWNtb9ZACE706Y";
    string val = "abcdefghiklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    map<char, char> dict;
    for (int i = 0; i < key.size(); i++) {
        dict[key[i]] = val[i];
    }

    for (int i = 0; i < ret.size(); i++) {
        if (ret[i] >= 'a' && ret[i] <= 'z' ||
            ret[i] >= 'A' && ret[i] <= 'Z' ||
            ret[i] >= '0' && ret[i] <= '9') {
            ret[i] = dict[ret[i]];
        }
    }

    return env->NewStringUTF(ret.c_str());
}