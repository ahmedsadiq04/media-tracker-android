package edu.metrostate.ics342.mediatracker.ui.detail;

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


data class QuoteData(
    val quoteText: String,
    val pageNumber: Int?,
    val isPublic: Boolean
)

@Composable
fun QuoteModal(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (QuoteData) -> Unit
) {
    if (!showDialog) return

    var quoteText by remember { mutableStateOf("") }
    var pageNumberText by remember { mutableStateOf("") }
    var isPublic by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Add Quote",
                    style = MaterialTheme.typography.headlineSmall
                )

                // Quote Text Input
                OutlinedTextField(
                    value = quoteText,
                    onValueChange = { newText ->
                        if (newText.length <= 500) { //bad to hard code value
                            quoteText = newText
                        }
                    },
                    label = { Text("Quote Text") },
                    placeholder = { Text("Enter the quote...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 5,
                    supportingText = {
                        Text(
                            text = "${quoteText.length} / $500",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }
                )

                // Optional Page Number Input
                OutlinedTextField(
                    value = pageNumberText,
                    onValueChange = { input ->
                        // Only allow numeric input
                        if (input.all { it.isDigit() }) {
                            pageNumberText = input
                        }
                    },
                    label = { Text("Page Number (Optional)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Is Public Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Public Quote",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Allow others to see this quote",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = isPublic,
                        onCheckedChange = { isPublic = it }
                    )
                }

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        enabled = quoteText.isNotBlank(),
                        onClick = {
                            val parsedPageNumber = pageNumberText.toIntOrNull()
                            onSave(
                                QuoteData(
                                    quoteText = quoteText.trim(),
                                    pageNumber = parsedPageNumber,
                                    isPublic = isPublic
                                )
                            )
                            onDismiss()
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}