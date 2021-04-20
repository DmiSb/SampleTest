package ru.dmisb.sample_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.util.*

class SampleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var topBoxColor = Color.WHITE
    private var bottomBoxColor = Color.RED
    private var logoTextColor = Color.BLACK
    private var bonusesTextColor = Color.BLACK
    private var hintTextColor = Color.GRAY

    private var topBoxHeight = context.dpToPx(132)
    private var bottomBoxHeight = context.dpToPx(148)

    private var logoText: String = ""

    private val tvLogo: TextView
    private val tvBonuses: TextView
    private val tvBurnDate: TextView
    private val tvBurnBonuses: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_view, this, true)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SampleView)
            topBoxColor = typedArray.getColor(R.styleable.SampleView_top_box_color, Color.WHITE)
            bottomBoxColor = typedArray.getColor(R.styleable.SampleView_bottom_box_color, Color.RED)
            logoTextColor = typedArray.getColor(R.styleable.SampleView_logo_text_color, Color.BLACK)
            bonusesTextColor = typedArray.getColor(R.styleable.SampleView_bonuses_text_color, Color.BLACK)
            hintTextColor = typedArray.getColor(R.styleable.SampleView_hint_text_color, Color.GRAY)

            logoText = typedArray.getString(R.styleable.SampleView_logo_text).orEmpty()

            val topHeight = typedArray.getDimension(R.styleable.SampleView_top_box_height, 0f)
            if (topHeight > topBoxHeight)
                topBoxHeight = topHeight

            val bottomHeight = typedArray.getDimension(R.styleable.SampleView_bottom_box_height, 0f)
            if (bottomHeight > bottomBoxHeight)
                bottomBoxHeight = bottomHeight

            typedArray.recycle()
        }

        tvLogo = view.findViewById(R.id.tv_logo)
        tvBonuses = view.findViewById(R.id.tv_bonuses)
        tvBurnDate = view.findViewById(R.id.tv_burn_date)
        tvBurnBonuses = view.findViewById(R.id.tv_burn_bonuses)

        val topBox = view.findViewById<View>(R.id.top_box)
        topBox.setBackgroundColor(topBoxColor)
        val topBoxLP = topBox.layoutParams
        topBoxLP.height = topBoxHeight.toInt()
        topBox.layoutParams = topBoxLP

        val bottomBox = view.findViewById<View>(R.id.bottom_box)
        bottomBox.setBackgroundColor(bottomBoxColor)
        val bottomBoxLP = bottomBox.layoutParams
        bottomBoxLP.height = bottomBoxHeight.toInt()
        bottomBox.layoutParams = bottomBoxLP

        tvLogo.setTextColor(logoTextColor)
        tvLogo.text = logoText

        tvBonuses.setTextColor(bonusesTextColor)
        tvBurnDate.setTextColor(hintTextColor)
        tvBurnBonuses.setTextColor(hintTextColor)
    }

    fun bind(currentQuantity: Double?, forBurningQuantity: Double?, dateBurning: Date?) {
        val bonuses = currentQuantity.orZero().toInt()
        tvBonuses.text = context.resources.getQuantityString(R.plurals.bonuses, bonuses, bonuses)

        val burnBonuses = forBurningQuantity.orZero().toInt()
        tvBurnBonuses.text = context.resources.getQuantityString(R.plurals.bonuses, burnBonuses, burnBonuses)

        if (dateBurning != null) {
            val format = SimpleDateFormat("dd.MM", Locale.getDefault())
            tvBurnDate.text = context.getString(R.string.burning_date, format.format(dateBurning))
        }
    }
}