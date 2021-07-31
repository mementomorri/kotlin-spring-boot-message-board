package me.mementomorri

import me.mementomorri.Model.connectToServer
import me.mementomorri.Model.tweetChannel
import io.kvision.Application
import io.kvision.core.*
import io.kvision.form.text.textAreaInput
import io.kvision.html.*
import io.kvision.module
import io.kvision.panel.hPanel
import io.kvision.panel.root
import io.kvision.panel.vPanel
import io.kvision.startApplication
import io.kvision.utils.ENTER_KEY
import io.kvision.utils.perc
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.w3c.dom.events.KeyboardEvent

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class App : Application() {
    override fun start() {
        root("root") {
            vPanel(JustifyContent.CENTER, AlignItems.CENTER, spacing = 5) {
                margin = 10.px
                width = 100.perc
                label("Username")
                val nickname = textAreaInput {
                    color= Color("#c8c8c8")
                    background=Background(Color("#222"))
                    value="Guest"
                    width = 350.px
                }
                label("Hashtag#")
                val tags = textAreaInput {
                    color= Color("#c8c8c8")
                    background=Background(Color("#222"))
                    width = 350.px
                    height = 38.px
                }
                hPanel(justify = JustifyContent.SPACEBETWEEN) {
                    width = 350.px
                    val tweet = textAreaInput {
                        color= Color("#c8c8c8")
                        background=Background(Color("#222"))
                        value="Hello world!"
                        width = 280.px
                        height = 120.px
                    }

                    val button = button("Post") {
                        border= Border(CssSize(2,UNIT.px),BorderStyle.SOLID, Color("#c8c8c8"))
                        width = 70.px
                        height = 100.perc
                        background= Background(Color("#333"))
                    }

                    fun post() {
                        AppScope.launch {
                            val tagList = tags.value?.let {
                                it.split(" ").map { it.replace("#", "").replace(",", "").trim() }
                                    .filter { it.isNotBlank() }
                            } ?: listOf()
                            nickname.value?.let { n ->
                                tweet.value?.let { v ->
                                    tweetChannel.send(Tweet(null, n, v, tagList))
                                    tweet.value = null
                                    tags.value = null
                                    tweet.focus()
                                }
                            }
                        }
                    }

                    fun keyDownHandler(ev: KeyboardEvent) {
                        if (ev.ctrlKey && ev.keyCode == ENTER_KEY) {
                            post()
                        }
                    }

                    tweet.onEvent {
                        keydown = ::keyDownHandler
                    }

                    tags.onEvent {
                        keydown = ::keyDownHandler
                    }

                    button.onClick {
                        post()
                    }

                }
                label("Messages")
                add(TweetPanel())
                ul(className = "icons") {
                    li(className = "icons") { link("","https://github.com/mementomorri",className = "fa fa-github")  }
                    li(className = "icons") { link("","https://mementomorri.github.io/",className = "fa fa-dribbble") }
                }
            }
        }
        connectToServer()
    }
}

fun main() {
    startApplication(::App, module.hot)
}
