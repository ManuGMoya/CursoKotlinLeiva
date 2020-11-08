package com.manugmoya.kotlinleiva

import android.view.View
import android.view.ViewGroup
import androidx.annotation.WorkerThread

fun getItems1(): MutableList<MediaItem> {

    val listMedia = mutableListOf<MediaItem>()

    for (i in 1..10) {
        listMedia.add(
            MediaItem(
                "Title $i", "https://placekitten.com/200/200?image=$i", if (i % 3 == 0) {
                    MediaItem.Type.PHOTO
                } else {
                    MediaItem.Type.VIDEO
                }
            )
        )
    }

/*    val listOf = listOf(
        MediaItem("Title 3", "https://placekitten.com/200/200?image=3", MediaItem.Type.PHOTO),
        MediaItem("Title 4", "https://placekitten.com/200/200?image=4", MediaItem.Type.PHOTO),
        MediaItem("Title 1", "https://placekitten.com/200/200?image=1", MediaItem.Type.PHOTO),
        MediaItem("Title 5", "https://placekitten.com/200/200?image=5", MediaItem.Type.VIDEO),
        MediaItem("Title 6", "https://placekitten.com/200/200?image=6", MediaItem.Type.PHOTO),
        MediaItem("Title 2", "https://placekitten.com/200/200?image=2", MediaItem.Type.PHOTO),
        MediaItem("Title 7", "https://placekitten.com/200/200?image=7", MediaItem.Type.VIDEO),
        MediaItem("Title 8", "https://placekitten.com/200/200?image=8", MediaItem.Type.PHOTO),
        MediaItem("Title 9", "https://placekitten.com/200/200?image=9", MediaItem.Type.PHOTO),
        MediaItem("Title 10", "https://placekitten.com/200/200?image=10", MediaItem.Type.VIDEO)
    )*/
    return listMedia
}

//@WorkerThread -> anotación para indicar que no se puede ejecutar en el hilo principal
object MediaProvider {
    @WorkerThread
    fun getItems(): List<MediaItem> {
        Thread.sleep(2000)
        return (1..10).map {
            (MediaItem(
                "Title $it", "https://placekitten.com/200/200?image=$it",
                if (it % 3 == 0) MediaItem.Type.PHOTO else MediaItem.Type.VIDEO
            ))
        }
    }
}

