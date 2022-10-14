package de.maddin.tastetestviewer.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class SuccessController {

    @GetMapping("/success")
    fun getSuccessPage(
        @RequestParam(required = false) message: String?,
        model: Model,
    ): ModelAndView {
        message
            ?.let { model.addAttribute("message", it) }
        return ModelAndView("success")
    }
}

@Controller
class FailureController {

    @GetMapping("/failure")
    fun getFailurePage(
        error: String,
        message: String,
        model: Model,
    ): ModelAndView {
        model.addAttribute("error", error)
        model.addAttribute("message", message)
        return ModelAndView("failure")
    }
}