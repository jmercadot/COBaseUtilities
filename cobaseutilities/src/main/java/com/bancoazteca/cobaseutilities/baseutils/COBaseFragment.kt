package com.bancoazteca.cobaseutilities.baseutils

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bancoazteca.cobaseutilities.R

open class COBaseFragment : Fragment() {
    fun <T : Fragment?> mostrarFragmentFade(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        var fragment =
            fragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragment!!.arguments = bundle
            } catch (e: Exception) {
                throw RuntimeException("New Fragment should have been created", e)
            }
        }
        fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
        fragmentTransaction.replace(containerViewId, fragment, fragmentClass.simpleName)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    open fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean
    ) {
        mostrarFragment(fragmentClass, containerViewId, bundle, addToBackStack, false)
    }

    open fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        clearStack: Boolean
    ) {
        mostrarFragment(fragmentClass, containerViewId, bundle, addToBackStack, clearStack, false)
    }

    open fun <T : Fragment?> mostrarFragment(
        fragmentClass: Class<T>,
        containerViewId: Int,
        bundle: Bundle?,
        addToBackStack: Boolean,
        clearStack: Boolean,
        commitAllowingStateLoss: Boolean
    ) {
        val fragmentManager = childFragmentManager
        if (clearStack) {
            for (i in 0 until fragmentManager.backStackEntryCount) {
                fragmentManager.popBackStack()
            }
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = fragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragment!!.arguments = bundle
            } catch (e: java.lang.Exception) {
                throw java.lang.RuntimeException("New Fragment should have been created", e)
            }
        }
        fragmentTransaction.replace(containerViewId, fragment!!, fragmentClass.simpleName)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentClass.name)
        }
        if (commitAllowingStateLoss) {
            fragmentTransaction.commitAllowingStateLoss()
        } else {
            fragmentTransaction.commit()
        }
    }

    protected fun lanzarActivity(intent: Intent?, flags: Int) {
        (activity as COBaseActivity).lanzarActivity(intent!!, flags)
    }

    protected fun lanzarActivityForResult(intent: Intent?, requestCode: Int) {
        val activity = activity as COBaseActivity?
        startActivityForResult(intent, requestCode)
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    fun progress(it: Boolean) {
        (activity as COBaseActivity).progress(it)
    }

    fun mostrarAlerta(titulo: String, mensaje: String) {
        (activity as COBaseActivity).mostrarAlerta(titulo, mensaje, requireActivity())
    }

    fun mostrarAlerta(
        titulo: String, mensaje: String, textoPositivo: String,
        listenerPositivo: DialogInterface.OnClickListener
    ) {
        (activity as COBaseActivity).mostrarAlerta(
            titulo,
            mensaje,
            requireActivity(),
            textoPositivo,
            listenerPositivo
        )
    }

    fun mostrarAlerta(
        titulo: String, mensaje: String, textoPositivo: String,
        listenerPositivo: DialogInterface.OnClickListener,
        textoNegativo : String,
        listenerNegativo: DialogInterface.OnClickListener
    ) {
        (activity as COBaseActivity).mostrarAlerta(
            titulo,
            mensaje,
            requireActivity(),
            textoPositivo,
            listenerPositivo,
            textoNegativo,
            listenerNegativo
        )
    }

    fun mostrarErrorSnackBar(mensaje: String) {
        (activity as COBaseActivity).mostrarErrorSnackBar(mensaje)
    }

    fun closeKeyboard() {
        (activity as COBaseActivity).closeKeyboard(requireContext(), requireView())
    }
}