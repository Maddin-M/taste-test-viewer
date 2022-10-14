package de.maddin.tastetestviewer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TasteTestViewerApplication

fun main(args: Array<String>) {
	runApplication<TasteTestViewerApplication>(*args)
}
