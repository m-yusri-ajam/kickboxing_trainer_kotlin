import com.misterri.kickboxingtrainer.Combo

val STARTER_COMBOS = listOf(
    // Intensity 1: Low (Focus on 1-2 hits)
    Combo(name = "1-2", intensity = 1, type = "Hand Only"),
    Combo(name = "2-3", intensity = 1, type = "Hand Only"),
    Combo(name = "1-7", intensity = 1, type = "Hand & Kick"),

    // Intensity 2: Medium (Focus on 3-4 hits, basic defense)
    Combo(name = "1-2-3", intensity = 2, type = "Hand Only"),
    Combo(name = "2-3-2", intensity = 2, type = "Hand Only"),
    Combo(name = "2-BLOCK-3", intensity = 2, type = "Defense & Counter"),

    // Intensity 3: High (Focus on 4+ hits, complex movements)
    Combo(name = "1-2-BLOCK-3-2", intensity = 3, type = "Defense & Counter"),
    Combo(name = "1-2-3-4-5", intensity = 3, type = "Hand Only"),
    Combo(name = "1-2-3-8", intensity = 3, type = "Hand & Kick")
)