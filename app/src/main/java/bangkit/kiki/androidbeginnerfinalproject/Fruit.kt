package bangkit.kiki.androidbeginnerfinalproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val name: String,
    val scientificName: String,
    val description: String,
    val nutrition: String,
    val benefit: String,
    val photo: String
): Parcelable
