package com.bancoazteca.cobaseutilities.baseutils

import android.content.Intent
import com.bancoazteca.cobaseutilities.baseutils.COBaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class COBaseBottomSheetDialog : BottomSheetDialogFragment() {
    protected fun lanzarActivity(intent: Intent?, flags: Int) {
        (activity as COBaseActivity).lanzarActivity(intent!!, flags)
    }
}