package edu.metrostate.ics342.mediatracker.ui.quotes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.R
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.Quote
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit
import edu.metrostate.ics342.mediatracker.ui.detail.MediaDetailViewModel
import edu.metrostate.ics342.mediatracker.ui.detail.QuoteModal
import edu.metrostate.ics342.mediatracker.ui.library.LibraryViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

//TODO: Add Load Next Section when Scroll to Bottom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    viewModel: QuotesViewModel = viewModel()
) {
    val quotes by viewModel.quotes.collectAsStateWithLifecycle()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("My Quotes", "Public Quotes")

    var showQuoteModal by remember { mutableStateOf(false) }
    var selectedQuoteEdit by remember { mutableStateOf<Quote?>(null) }

    // Helper to open modal for creation
    fun openCreateModal() {
        selectedQuoteEdit = null
        showQuoteModal = true
    }

    // Helper to open modal for editing
    fun openEditModal(quote: Quote) {
        selectedQuoteEdit = quote
        showQuoteModal = true
    }

    // Function called when a tab is clicked
    fun onQuoteFilterChanged(index: Int) {
        selectedTabIndex = index
        when (index) {
            0 -> viewModel.getMyQuotes()
            1 -> viewModel.getPublicQuotes()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getMyQuotes()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.nav_quotes)) })

        //Only when quote is being edited
        QuoteModal(
            showDialog = showQuoteModal,
            onDismiss = { showQuoteModal = false },
            onSave = { quoteData ->
                //save to viewmodel
                //viewModel.CreateQuote(mediaId, quoteData.quoteText, quoteData.pageNumber, quoteData.isPublic)
            }
        )

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onQuoteFilterChanged(index) },
                    text = { Text(text = title) }
                )
            }
        }

        //Loading Circle if Quotes is Empty
        if (quotes.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Column
        } else {
            //Scrollview for Quotes
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = quotes,
                    key = { quote -> quote.id }
                ) { quote ->
                    val isMyQuote = selectedTabIndex == 0;

                    QuoteCard(
                        quote = quote,
                        onClick = {
                            if (isMyQuote) {
                                openEditModal(quote)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun QuoteCard(
    quote: Quote,
    onLikeClick: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = quote.media.title ?: "Unknown Media",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = if (quote.isPublic) Icons.Default.Public else Icons.Default.Lock,
                    contentDescription = if (quote.isPublic) "Public Quote" else "Private Quote",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.height(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(2.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "“${quote.quoteText}”",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (quote.pageNumber != null && quote.pageNumber > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "— Page ${quote.pageNumber}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onLikeClick,
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Like quote",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                    Text(
                        text = "${quote.likeCount}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = formatQuoteDate(quote.createdAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

//Helper func to turn it from isoString to local time
fun formatQuoteDate(isoString: String): String {
    return try {
        val parsed = OffsetDateTime.parse(isoString)
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.getDefault())
        parsed.format(formatter)
    } catch (e: Exception) {
        isoString //fails to convert, return the value given
    }
}