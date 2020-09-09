package `in`.abhisheksaxena.baaten.ui.base

import `in`.abhisheksaxena.baaten.R
import `in`.abhisheksaxena.baaten.utils.EventObserver
import `in`.abhisheksaxena.baaten.utils.ToasterStyle
import `in`.abhisheksaxena.toaster.Toaster
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 10:12
 */

abstract class AbstractFragment<B : ViewDataBinding, VM : AbstractViewModel> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected lateinit var binding: B

    protected abstract val viewModel: VM

    private var progressbar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<B>(inflater, layoutRes, container, false).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
    }?.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressbar = requireActivity().findViewById(R.id.progress_bar)
        subscribeToObservers()
        onViewCreated(savedInstanceState)
    }

    protected abstract fun onViewCreated(savedInstanceState: Bundle?)

    protected open fun subscribeToObservers() {
        viewModel.snackbarText.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.progressbarStatus.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                showProgressbar()
            } else {
                hideProgressbar()
            }
        })

        viewModel.toasterText.observe(viewLifecycleOwner, EventObserver { toast ->
            when (toast.type) {
                ToasterStyle.Type.DEFAULT -> Toaster.pop(
                    requireContext(),
                    toast.message,
                    Toaster.LENGTH_SHORT
                ).show()
                ToasterStyle.Type.DEFAULT_WITH_IMG -> {
                    if (toast.drawableRes != null) {
                        Toaster.pop(
                            requireContext(),
                            toast.message,
                            toast.drawableRes!!,
                            Toaster.LENGTH_SHORT
                        ).show()
                    } else {
                        Toaster.pop(requireContext(), toast.message, Toaster.LENGTH_SHORT).show()
                    }
                }
                ToasterStyle.Type.WARNING -> Toaster.popWarning(
                    requireContext(),
                    toast.message,
                    Toaster.LENGTH_SHORT
                ).show()
                ToasterStyle.Type.SUCCESS -> Toaster.popSuccess(
                    requireContext(),
                    toast.message,
                    Toaster.LENGTH_SHORT
                ).show()
                ToasterStyle.Type.ERROR -> Toaster.popError(
                    requireContext(),
                    toast.message,
                    Toaster.LENGTH_SHORT
                ).show()
                ToasterStyle.Type.CUSTOM -> { }
            }
        })
    }

    protected fun showProgressbar() {
        progressbar?.visibility = View.VISIBLE
    }

    protected fun hideProgressbar() {
        progressbar?.visibility = View.GONE
    }
}