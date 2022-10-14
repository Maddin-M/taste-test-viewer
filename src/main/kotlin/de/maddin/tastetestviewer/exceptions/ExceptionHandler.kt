package de.maddin.tastetestviewer.exceptions

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.lang.Exception

@ControllerAdvice("de.maddin.tastetestviewer")
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(
        e: Exception,
        redirectAttributes: RedirectAttributes,
    ) : RedirectView {
        redirectAttributes.addAttribute("error", e.javaClass.name)
        redirectAttributes.addAttribute("message", e.message)
        return RedirectView("/failure")
    }
}