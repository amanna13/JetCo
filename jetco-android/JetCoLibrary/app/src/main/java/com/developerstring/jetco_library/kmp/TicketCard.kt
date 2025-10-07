package com.developerstring.jetco_library.kmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerstring.jetco.ui.cards.ticket.TicketCard
import com.developerstring.jetco.ui.cards.ticket.TicketCardCorner
import com.developerstring.jetco.ui.cards.ticket.TicketContent
import com.developerstring.jetco_library.R

@Composable
fun TicketCardSimple() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TicketCard(
            modifier = Modifier
                .width(250.dp)
                .height(350.dp)
        ) {
            TicketContent(
                topContent = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Simple Ticket Card", fontWeight = FontWeight.Bold)
                    }
                },
                bottomContent = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Take it easy!", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    }
}


@Composable
fun TicketCardCustom() {

    val normalFontSize = 12.sp
    val largeFontSize = 16.sp

    val scrollState = rememberScrollState()

    val linearGradient = Brush.linearGradient(
        Pair(0f, Color.White),
        Pair(1f, Color(0xFFC0E5FF)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TicketCard(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
                .height(450.dp),
            dividerColor = Color.Gray,
            cornerRadius = TicketCardCorner(
                topRight = 50.dp,
                topLeft = 50.dp,
                bottomLeft = 0.dp,
                bottomRight = 0.dp
            ),
            notchRadius = 30.dp,
            cardColor = Color.White,
            cardBrush = linearGradient,
        ) {
            TicketContent(topContent = {
                Column(
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your Journey",
                        fontWeight = FontWeight.Bold,
                        fontSize = largeFontSize
                    )
                    Text(
                        "(Aditya S.)",
                        fontSize = normalFontSize,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF8CD1FF)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "PNQ",
                                    fontSize = largeFontSize,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "13:00",
                                    fontSize = normalFontSize,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    "10/09/2025",
                                    fontSize = normalFontSize,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    "T-1",
                                    fontSize = normalFontSize,
                                    fontWeight = FontWeight.Normal
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                                    .background(color = Color.Blue.copy(0.5f)),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    modifier = Modifier.rotate(90f),
                                    painter = painterResource(R.drawable.round_flight_24),
                                    contentDescription = "flight_logo",
                                    tint = Color.White
                                )

                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "BLR",
                                    fontSize = largeFontSize,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "14:15",
                                    fontSize = normalFontSize,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    "10/09/2025",
                                    fontSize = normalFontSize,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    "T-2",
                                    fontSize = normalFontSize,
                                    fontWeight = FontWeight.Normal
                                )
                            }

                        }
                    }
                }
            }, bottomContent = {
                Column(
                    Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        painter = painterResource(R.drawable.img),
                        contentDescription = "bar_code",
                        contentScale = ContentScale.Crop
                    )
                    Text("Have a safe journey! Enjoy your trip.", fontSize = normalFontSize)
                }
            })
        }
    }
}
