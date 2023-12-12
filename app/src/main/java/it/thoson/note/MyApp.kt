import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import it.thoson.note.ui.screens.note_editor.EditMode
import it.thoson.note.ui.screens.note_editor.NoteEditor

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(
                navController = navController,
            )
        }

        composable("home_screen") {
            HomeScreen(
                navController = navController,
            )
        }

        composable(
            "note_editor?mode={mode}&editingNoteId={editingNoteId}&previewTitle={previewTitle}&previewContent={previewContent}",
            arguments = listOf(navArgument("mode") { type = NavType.StringType },
                navArgument("editingNoteId") { type = NavType.LongType },
                navArgument("previewTitle") { type = NavType.StringType },
                navArgument("previewContent") { type = NavType.StringType })
        ) {
            NoteEditor(
                navController = navController,
                mode = EditMode.valueOf(it.arguments!!.getString("mode")!!),
                editingNoteId = it.arguments?.getLong("editingNoteId"),
                previewTitle = it.arguments?.getString("previewTitle"),
                previewContent = it.arguments?.getString("previewContent"),
            )
        }

        composable(
            "note_editor?mode={mode}&editingNoteId={editingNoteId}", arguments = listOf(
                navArgument("mode") { type = NavType.StringType },
                navArgument("editingNoteId") { type = NavType.LongType },
            )
        ) {
            NoteEditor(
                navController = navController,
                mode = EditMode.valueOf(it.arguments!!.getString("mode")!!),
                editingNoteId = it.arguments?.getLong("editingNoteId"),
                previewTitle = null,
                previewContent = null,
            )
        }

        composable(
            "note_editor?mode={mode}&previewTitle={previewTitle}&previewContent={previewContent}",
            arguments = listOf(
                navArgument("mode") { type = NavType.StringType },
                navArgument("previewTitle") { type = NavType.StringType },
                navArgument("previewContent") { type = NavType.StringType },
            )
        ) {
            NoteEditor(
                navController = navController,
                mode = EditMode.valueOf(it.arguments!!.getString("mode")!!),
                editingNoteId = null,
                previewTitle = it.arguments?.getString("previewTitle"),
                previewContent = it.arguments?.getString("previewContent"),
            )
        }

        composable(
            "note_editor?mode={mode}", arguments = listOf(
                navArgument("mode") { type = NavType.StringType },
            )
        ) {
            NoteEditor(
                navController = navController,
                mode = EditMode.valueOf(it.arguments!!.getString("mode")!!),
                editingNoteId = null,
                previewTitle = null,
                previewContent = null,
            )
        }
    }
}