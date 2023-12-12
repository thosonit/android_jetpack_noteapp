import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import it.thoson.note.ui.screens.note_editor.EditMode
import it.thoson.note.ui.theme.AppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import it.thoson.note.database.DatabaseProvider
import it.thoson.note.database.NoteDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeScreenVM = viewModel()
) {
    val itemList by viewModel.notes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllNotes()
    }

    DisposableEffect(Unit) {
        onDispose {
            println("Dispose Composable")
        }
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), content = {
        Scaffold(topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ), title = {
                Text(
                    "Notes",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }, actions = {
                IconButton(modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                    onClick = { /* Handle search action */ }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                    onClick = { /* Handle info action */ }) {
                    Icon(
                        Icons.Default.Info, contentDescription = "Info", tint = Color.White
                    )
                }
            })
        }, floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.background,
                onClick = {
                    navController.navigate("note_editor?mode=${EditMode.CREATE.name}")
                },
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                items(itemList.size) { it ->
                    NoteCard(
                        note = itemList[it],
                        onNoteClick = {
                            navController.navigate("note_editor?mode=${EditMode.PREVIEW.name}&editingNoteId=${it.id}&previewTitle=${it.title}&previewContent=${it.content}")
                        },
                        onDeleteClick = {
                            viewModel.deleteNote(it)
                            viewModel.getAllNotes()
                        },
                    )
                }
            }
        }
    })
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    AppTheme {
        HomeScreen(navController = navController, viewModel = viewModel())
    }
}