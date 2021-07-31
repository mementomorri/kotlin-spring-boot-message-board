package me.mementomorri

import io.kvision.core.*
import io.kvision.data.DataContainer
import io.kvision.data.SorterType
import io.kvision.html.label
import io.kvision.html.link
import io.kvision.html.span
import io.kvision.panel.SimplePanel
import io.kvision.panel.VPanel
import io.kvision.panel.hPanel
import io.kvision.types.toStringF
import io.kvision.utils.perc
import io.kvision.utils.px

class TweetPanel : SimplePanel() {
    init {
        border = Border(1.px, BorderStyle.SOLID, Color.name(Col.SILVER))
        width = 350.px
        height = 402.px


        val dataContainer = DataContainer(Model.tweets, { tweet, _, _ ->
            Post(tweet)
        }, sorter = {
            it.date?.getTime()
        }, sorterType = {
            SorterType.DESC
        }, container = VPanel(spacing = 1).apply {
            height = 400.px
            overflow = Overflow.AUTO
        })
        add(dataContainer)
    }
}

class Post(tweet: Tweet) : VPanel(spacing = 2) {
    init {
        marginRight = 2.px
        marginLeft = 2.px
        padding = 5.px
        borderBottom = Border(1.px, BorderStyle.INSET, Color.name(Col.SILVER))
        hPanel(justify = JustifyContent.SPACEBETWEEN) {
            span(tweet.nickname) {
                fontWeight = FontWeight.BOLD
            }
            span(tweet.date?.toStringF()) {
                fontSize = 90.perc
            }
        }
        span(tweet.message)
        hPanel(spacing = 3, wrap = FlexWrap.WRAP) {
            tweet.tags.forEach {
                link("#$it") {
                    fontSize = 90.perc
                }
            }
        }
    }
}
