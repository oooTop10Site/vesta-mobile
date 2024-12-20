package com.example.vesta.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vesta.domain.modelsUI.ProductsDataResponseUi
import com.example.vesta.strings.VestaResourceStrings


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CartItem(
    product: ProductsDataResponseUi,
    onClick: ()-> Unit,
){
    Surface(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 10.dp, vertical = 7.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 5.dp) {


        Row(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.background)
                .clickable(onClick = onClick)
        ){
            Box( Modifier.fillMaxHeight().weight(1f)){
                CustomAsyncImage(product.image, modifier = Modifier.fillMaxSize())
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .weight(3f),
                verticalArrangement = Arrangement.Center) {

                Text(
                    text = product.nameKorr,
                    fontSize = 15.sp,
                    softWrap = true,
                    modifier = Modifier.weight(3f),
                    maxLines = 6
                )

                Column(
                    Modifier.fillMaxWidth()
                        .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)) {
                    Row(Modifier.fillMaxWidth()){
                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally){
                            if(product.model.isNotEmpty()){
                                Text(text = VestaResourceStrings.product_code,
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center)
                                Text(text = product.model,
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    fontSize = 15.sp,
                                    softWrap = true,
                                    textAlign = TextAlign.Center,
                                    maxLines = 2)
                                Spacer(Modifier.height(7.dp))
                            }
                        }
                        Spacer(Modifier.width(10.dp))
                        Column(
                            Modifier.weight(1.25f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally){
                            Row(Modifier.fillMaxWidth()){
                                CounterBlock(
                                    onIncrement = {},
                                    onDeIncrement = {},
                                    modifier = Modifier.weight(3f)
                                )
                                CardButton(modifier = Modifier.weight(1f)) { }
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth()){
                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = product.quantityStatus,
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 15.sp,
                                softWrap = true,
                                textAlign = TextAlign.Center,
                                maxLines = 2)
                        }
                        Spacer(Modifier.width(10.dp))
                        Column(
                            Modifier.weight(1.25f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "${product.price} ${VestaResourceStrings.rub}",
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 15.sp,
                                softWrap = true,
                                maxLines = 2)
                        }
                    }
                }
            }
        }
        }


}