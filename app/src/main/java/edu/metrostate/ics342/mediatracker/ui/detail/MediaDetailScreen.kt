package edu.metrostate.ics342.mediatracker.ui.detail

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToInt

//Turns Number to valid string
fun Long.toReadableString(): String {
    if (this < 1000) return this.toString()

    val suffixes = charArrayOf('k', 'M', 'B', 'T')
    val value = this.toDouble()
    val exp = (log10(value) / 3).toInt()
    val scaledValue = value / 1000.0.pow(exp.toDouble())
    val formatter = DecimalFormat("0.#")

    return "${formatter.format(scaledValue)}${suffixes[exp - 1]}"
}

// ── STUB — Students build this in Week 7 ─────────────────────────────────────
//
// Week 7 task: Build the Media Detail screen.
//   1. Receive mediaId from the navigation argument (typed Int — see NavGraph).
//   2. Call GET /media/{mediaId} to load full details.
//   3. Display: cover image, title, creator credit, metadata grid, genre chips,
//      average rating, description, and a library status control.
//   4. Display the reviews list from GET /reviews?mediaId={id}.
//   5. Handle loading and error states (full-screen — no half-built screens).

//TODO: Put Padding as const value at top for easy edits
@Composable
fun MediaDetailScreen(
    mediaId: Int,
    onNavigateBack: () -> Unit,
    onWriteReview: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    val loadedMedia = Media(
        id = mediaId,
        mediaType = "book",
        title = "Big Sur",
        author = "Apple Inc.",
        coverUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzlCFWKA3mxT7PrDdnl1kalityeJ3_H1ZFks7QE7SkiPWDPShMBWLpUc0&s=10",
        publishedYear = 2020,
        averageRating = 4.8f,
        ratingCount = 15500000, //15.5M mac books sold
        genres = listOf("Technology"),
        description = "Apple Inc. is an American multinational technology company headquartered in Cupertino, California. Founded in 1976 by Steve Jobs, Steve Wozniak, and Ronald Wayne, it is globally renowned for hardware products like the iPhone, Mac, and iPad, as well as software, cloud services, and the App Store.",
        pageCount = 750,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState).weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Content for the Media Details

            //Cover Image
            Box(
                modifier = Modifier
                    .size(128.dp, 180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (loadedMedia.coverUrl != null) {
                    AsyncImage(
                        model              = loadedMedia.coverUrl,
                        contentDescription = loadedMedia.title,
                        contentScale       = ContentScale.Crop,
                        modifier           = Modifier.fillMaxSize()
                    )
                } else {
                    Surface(color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxSize()) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(when (loadedMedia.mediaType) {
                                "book" -> "📖"; "movie" -> "🎬"; "show" -> "📺"
                                else -> "?"
                            }, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                //Title
                Text(loadedMedia.title, style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold, maxLines = 2)
                Spacer(Modifier.height(2.dp))

                Spacer(Modifier.height(4.dp))

                //Author
                Text(loadedMedia.creatorCredit(LocalContext.current),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(Modifier.height(12.dp))

                //Stars / Rating
                val numStars = loadedMedia.averageRating.roundToInt()
                val ratingString = loadedMedia.ratingCount.toReadableString() //turns to k, M, etc...
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    //Stars
                    //Rating Float
                    Text(
                        text = buildString {
                            append("★".repeat(numStars))
                            append(" ${"%.1f".format(loadedMedia.averageRating)}")
                        },
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = "(${ratingString} reviews)",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.height(24.dp))

                //Row for Want to / Save
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp), horizontalArrangement = Arrangement.Center) {
                    Button(
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Want To",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(36.dp))

                    Button(
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Save",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                //About Section
                Text(
                    text = "About",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                loadedMedia.description?.let {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(Modifier.height(24.dp))

                //Year, Pages, and Genre Section
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    SkeletonBox("Year", loadedMedia.publishedYear.toString())

                    if (loadedMedia.mediaType == "book") {
                        SkeletonBox(title = "Pages", body = loadedMedia.pageCount.toString())
                    } else if (loadedMedia.mediaType == "movie") {
                        SkeletonBox(title = "Runtime", body = loadedMedia.runtimeMinutes.toString())
                    } else {
                        SkeletonBox(title = "Seasons", body = loadedMedia.seasonCount.toString())
                        SkeletonBox(title = "Episodes", body = loadedMedia.episodeCount.toString())
                    }
                }

                Spacer(Modifier.height(24.dp))

                //Reviews Section
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "Reviews (${ratingString})",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Write Review",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                //TODO: Field for the Reviews, Use Lazy load?
            }
        }
    }
}

@Composable
fun SkeletonBox(
    title: String,
    body: String,
) {
    Box(
        modifier = Modifier
            .width(125.dp).height(65.dp).padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}