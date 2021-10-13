package io.canvas.alta.util

import timber.log.Timber

class CustomDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            "[%s:%s][M:%s]",
            super.createStackElementTag(element),
            element.lineNumber,
            element.methodName
        )
    }
}