package it.thoson.note.ui.screens.note_editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Preview
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import it.thoson.note.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditor(
    navController: NavHostController,
    mode: EditMode,
    editingNoteId: Long?, //is null when mode == PREVIEW || CREATE
    previewTitle: String?, //is null when mode == EDIT || CREATE
    previewContent: String?, //is null when mode == EDIT || CREATE
    viewModel: NoteEditorVM = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    LaunchedEffect(key1 = true) {
        if (mode == EditMode.EDIT) {
            viewModel.getNote(editingNoteId!!)
        } else if (mode == EditMode.PREVIEW) {
            viewModel.setPreview(title = previewTitle ?: "", content = previewContent ?: "")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            println("Dispose Composable")
        }
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), content = {
        Scaffold(
            topBar = {
                TopAppBar(modifier = Modifier.padding(horizontal = 16.dp),
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                    navigationIcon = {
                        IconButton(modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.secondary),
                            onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.KeyboardArrowLeft,
                                contentDescription = "ArrowBack",
                                tint = Color.White
                            )
                        }
                    },
                    title = {
                    },
                    actions = {
                        if (mode == EditMode.PREVIEW && editingNoteId != null) {
                            IconButton(modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                                onClick = {
                                    navController.navigate("note_editor?mode=${EditMode.EDIT.name}&editingNoteId=${editingNoteId}")
                                }) {
                                Icon(
                                    Icons.Rounded.Edit,
                                    contentDescription = "Edit",
                                    tint = Color.White
                                )
                            }
                        }
                        if (mode == EditMode.EDIT || mode == EditMode.CREATE) {
                            IconButton(modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                                onClick = {
                                    navController.navigate("note_editor?mode=${EditMode.PREVIEW.name}&previewTitle=${viewModel.title}&previewContent=${viewModel.content}")
                                }) {
                                Icon(
                                    Icons.Rounded.Preview,
                                    contentDescription = "Preview",
                                    tint = Color.White
                                )
                            }
                        }
                        if (mode == EditMode.EDIT || mode == EditMode.CREATE) {
                            Spacer(modifier = Modifier.width(20.dp))
                            IconButton(modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                                onClick = {
                                    if (mode == EditMode.EDIT) {
                                        viewModel.updateNote(noteId = editingNoteId!!)
                                        navController.popBackStack()
                                    }
                                    if (mode == EditMode.CREATE) {
                                        viewModel.insertNote()
                                        navController.popBackStack()
                                    }
                                }) {
                                Icon(
                                    Icons.Default.Save,
                                    contentDescription = "Save",
                                    tint = Color.White
                                )
                            }
                        }
                    })
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                TextField(value = viewModel.title,
                    enabled = mode != EditMode.PREVIEW,
                    onValueChange = { newText -> viewModel.title = newText },
                    textStyle = LocalDensity.current.run {
                        TextStyle(
                            fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        // Handle "Done" action if needed
                    }),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            "Title",
                            color = Color(0xFF9A9A9A),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    })
                TextField(value = viewModel.content,
                    enabled = mode != EditMode.PREVIEW,
                    onValueChange = { newText -> viewModel.content = newText },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Transparent),
                    textStyle = LocalDensity.current.run {
                        TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        // Handle "Done" action if needed
                    }),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            "Type something...",
                            color = Color(0xFF9A9A9A),
                            fontSize = 16.sp,
                        )
                    })
            }
        }
    })
}

@Preview
@Composable
fun NoteEditorPreview() {
    val navController = rememberNavController()
    AppTheme {
        NoteEditor(
            navController = navController,
            mode = EditMode.EDIT,
            editingNoteId = null,
            previewTitle = null,
            previewContent = null
        )
    }
}