package edu.metrostate.ics342.mediatracker.ui.detail

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.Review
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit
import edu.metrostate.ics342.mediatracker.ui.auth.AuthViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToInt
import edu.metrostate.ics342.mediatracker.R as Rstr

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
    onWriteReview: (Int) -> Unit,
    viewModel: MediaDetailViewModel = viewModel()
) {
    //on first load or if mediaID changes, func is called
    LaunchedEffect(mediaId) {
        viewModel.setMediaId(mediaId)
    }

    val loadedMedia by viewModel.mediaDetail.collectAsStateWithLifecycle() //watches like React states
    val couldNotFind by viewModel.mediaError.collectAsStateWithLifecycle()
    val reviews by viewModel.reviews.collectAsStateWithLifecycle()

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
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
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
                if(couldNotFind) {
                    Surface(color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxSize()) {
                            Icon(
                                imageVector = Icons.Filled.ErrorOutline,
                                contentDescription = "Error",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                } else if(loadedMedia == null) {
                    CircularProgressIndicator()
                } else if (loadedMedia?.coverUrl != null) {
                    AsyncImage(
                        model              = loadedMedia?.coverUrl,
                        contentDescription = loadedMedia?.title,
                        contentScale       = ContentScale.Crop,
                        modifier           = Modifier.fillMaxSize()
                    )
                } else {
                    Surface(color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxSize()) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(when (loadedMedia?.mediaType) {
                                "book" -> "📖"; "movie" -> "🎬"; "show" -> "📺"
                                else -> "?"
                            }, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                //If Errored out
                if (couldNotFind) {
                    Text("Media Could Not Be Found", style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold, maxLines = 2)
                }

                //Title
                loadedMedia?.title?.let {
                    Text(it, style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold, maxLines = 2)
                }
                Spacer(Modifier.height(2.dp))

                Spacer(Modifier.height(4.dp))

                //Author
                loadedMedia?.creatorCredit(LocalContext.current)?.let {
                    Text(it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                Spacer(Modifier.height(12.dp))

                //Stars / Rating
                if(loadedMedia != null) {
                    val numStars: Int? = loadedMedia?.averageRating?.roundToInt()
                    val ratingString =
                        loadedMedia?.ratingCount?.toReadableString() //turns to k, M, etc...

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        //Stars
                        //Rating Float
                        Text(
                            text = buildString {
                                numStars?.let { append("★".repeat(it)) }
                                append(" ${"%.1f".format(loadedMedia?.averageRating)}")
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { /* TODO: add to library */ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(stringResource(Rstr.string.status_want_to))
                        }
                        OutlinedButton(
                            onClick = { /* TODO: save */ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(stringResource(Rstr.string.status_save))
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
                    loadedMedia?.description?.let {
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
                    if(loadedMedia != null) StatGrid(loadedMedia!!)

                    Spacer(Modifier.height(24.dp))

                    //Reviews Section
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
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
                            onClick = { onWriteReview(loadedMedia?.id ?: -1) },
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

                    ReviewList(reviews, viewModel, mediaId)
                }
            }
        }
    }
}

@Composable
private fun StatGrid(detail: Media) {
    val stats = buildList {
        detail.publishedYear?.let { add(stringResource(Rstr.string.detail_stat_year) to it.toString()) }
        when (detail.mediaType) {
            "book" -> detail.pageCount?.let {
                add(stringResource(Rstr.string.detail_stat_pages) to it.toString())
            }
            "movie" -> detail.runtimeMinutes?.let {
                add(stringResource(Rstr.string.detail_stat_runtime) to stringResource(Rstr.string.detail_runtime_minutes, it))
            }
            "show" -> detail.seasonCount?.let {
                add(stringResource(Rstr.string.detail_stat_seasons) to it.toString())
            }
        }
        detail.genres.firstOrNull()?.let {
            add(stringResource(Rstr.string.detail_stat_genre) to it)
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stats.forEach { (label, value) ->
            StatBox(label = label, value = value, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun StatBox(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun ReviewList(reviews: List<Review>, viewModel: MediaDetailViewModel, mediaId: Int) {
    if (reviews.isEmpty()) {
        Text(
            text = "No reviews",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().heightIn(max = 400.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(reviews) { review ->
                ReviewItem(review)
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }

            item{ //hopefully loads next chunk
                LaunchedEffect(Unit) {
                    val lastReview = reviews.lastOrNull()
                    if (lastReview != null) {
                        viewModel.loadReviews(mediaId, after = lastReview.createdAt)
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Placeholder for User Avatar if you have one
            Text(
                text = review.user?.username ?: "Anonymous",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "★".repeat(review.rating),
                color = MaterialTheme.colorScheme.secondary
            )
        }
        review.reviewText?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = review.createdAt,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}