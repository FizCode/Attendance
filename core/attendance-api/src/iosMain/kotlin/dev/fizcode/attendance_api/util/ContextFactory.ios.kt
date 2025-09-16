package dev.fizcode.attendance_api.util

import platform.Foundation.NSBundle
import platform.UIKit.UIApplication

actual class ContextFactory {
    actual fun getContext(): Any = NSBundle

    actual fun getApplication(): Any = UIApplication

    actual fun getActivity(): Any =
        UIApplication.sharedApplication.keyWindow?.rootViewController ?: ""
}
