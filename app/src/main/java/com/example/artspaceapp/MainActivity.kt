package com.example.artspaceapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var artworkImageView: ImageView
    private lateinit var artworkTitleTextView: TextView
    private lateinit var artworkAuthorTextView: TextView
    private lateinit var artworkDescriptionTextView: TextView
    private lateinit var previousButton: MaterialButton
    private lateinit var nextButton: MaterialButton

    private val artworks = listOf(
        Artwork(
            R.drawable.mona_lisa,
            "Mona Lisa",
            "Leonardo da Vinci",
            "An iconic portrait (1503-1519) of Lisa Gherardini, known for her enigmatic smile and soft background. It exemplifies da Vinci's sfumato technique and is displayed in the Louvre Museum."
        ),
        Artwork(
            R.drawable.starry_night,
            "Starry Night",
            "Vincent van Gogh",
            "Painted in 1889, this masterpiece depicts a swirling night sky over a village, reflecting van Gogh's emotional turmoil through vibrant colors and dynamic brushwork. It is housed in the Museum of Modern Art, New York."
        ),
        Artwork(
            R.drawable.scream,
            "The Scream",
            "Edvard Munch",
            "Created in 1893, this expressionist work portrays a figure against a tumultuous sky, symbolizing existential angst. The most famous version is at the National Gallery in Oslo."
        ),
        Artwork(
            R.drawable.night_watch,
            "The Night Watch",
            "Rembrandt van Rijn",
            "A 1642 group portrait of a militia company, noted for its dynamic composition and use of chiaroscuro. It is displayed at the Rijksmuseum in Amsterdam."
        ),
        Artwork(
            R.drawable.adele,
            "Portrait of Adele Bloch Bauer I",
            "Gustav Klimt",
            "Created in 1907, this portrait features Adele Bloch Bauer, a prominent Viennese socialite. The painting is renowned for its intricate use of gold leaf and decorative patterns, characteristic of Klimt's Art Nouveau style. It symbolizes beauty and femininity and is currently housed in the Neue Galerie in New York City."
        )
    )

    private var currentArtworkIndex = 0
    private var isFirstArtwork: Boolean = true
    private var isLastArtwork: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        artworkImageView = findViewById(R.id.artworkImageView)
        artworkTitleTextView = findViewById(R.id.artworkTitleTextView)
        artworkAuthorTextView = findViewById(R.id.artworkAuthorTextView)
        artworkDescriptionTextView = findViewById(R.id.artworkDescriptionTextView)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)

        updateArtworkDisplay()
        updateNavigationButtons()

        previousButton.setOnClickListener {
            navigateArtwork(false)
        }

        nextButton.setOnClickListener {
            navigateArtwork(true)
        }
    }

    private fun navigateArtwork(goForward: Boolean) {
        currentArtworkIndex = when {
            goForward -> (currentArtworkIndex + 1) % artworks.size
            else -> (currentArtworkIndex - 1 + artworks.size) % artworks.size
        }
        updateArtworkDisplay()
        updateNavigationButtons()
    }

    private fun updateArtworkDisplay() {
        val currentArtwork = artworks[currentArtworkIndex]
        artworkImageView.setImageResource(currentArtwork.imageResourceId)
        artworkTitleTextView.text = currentArtwork.title
        artworkAuthorTextView.text = currentArtwork.author
        artworkDescriptionTextView.text = currentArtwork.description
    }

    private fun updateNavigationButtons() {
        isFirstArtwork = currentArtworkIndex == 0
        isLastArtwork = currentArtworkIndex == artworks.size - 1

        previousButton.isEnabled = !isFirstArtwork
        nextButton.isEnabled = !isLastArtwork

        previousButton.alpha = if (isFirstArtwork) 0.5f else 1.0f
        nextButton.alpha = if (isLastArtwork) 0.5f else 1.0f
    }
}

data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val author: String,
    val description: String
)