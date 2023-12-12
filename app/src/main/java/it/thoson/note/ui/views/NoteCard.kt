import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import it.thoson.note.models.Note
import kotlin.text.Typography.ellipsis

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteCard(note: Note, onNoteClick: (Note) -> Unit, onDeleteClick: (Note) -> Unit) {
    RevealSwipe(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp)),
        directions = setOf(
            RevealDirection.EndToStart
        ),
        backgroundCardEndColor = Color.Red,
        hiddenContentEnd = {
            Icon(
                modifier = Modifier.padding(horizontal = 25.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.White
            )
        },
        onBackgroundEndClick = {
            onDeleteClick(note)
        },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNoteClick(note)
                },
            colors = CardDefaults.cardColors(
                containerColor = Utils.intToColor(note.id.toInt()),
            ),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = note.title,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    ),
                    modifier = Modifier.padding(bottom = 4.dp),
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = note.content,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}