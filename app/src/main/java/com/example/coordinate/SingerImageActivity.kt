package com.example.coordinate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aboutview.DeviceModel
import com.example.aboutview.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_singer_img.*
import com.google.gson.reflect.TypeToken


class SingerImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_singer_img)

        val url = "https://publicd6tn2upk7h3ce.blob.core.windows.net/kubota/fig/7F6/7F61200/7F61200112/images/7F61200112.png?se=2020-05-18T09%3A00%3A40Z&sig=PbW3NPfPARDXqqKoehYSNbwkkNh0xKRuuWXgaCvKxSE%3D&sp=r&spr=https&sr=b&sv=2015-04-05"
        singer_img.onDeviceClicked = { id, rect ->
            Toast.makeText(this, "click id = $id, position = $rect", Toast.LENGTH_SHORT).show()
        }
        singer_img.setDataSource(url, getData(), true)

        singer_img.postDelayed({
            singer_img.setHighLightDataIds(arrayListOf(5,6,7))//part_id
        }, 3000)
    }

    private fun getData(): List<DeviceModel> {
        val type = object : TypeToken<List<DeviceModel>>() {}.type
        val list = Gson().fromJson<List<DeviceModel>>(datasource, type)
        return list
    }

    val datasource = "[\n" +
            "        {\n" +
            "         \"part_id\":1,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 1885,\n" +
            "        \"end_x\": 1982,\n" +
            "        \"start_y\": 300,\n" +
            "        \"end_y\": 349\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":2,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 365,\n" +
            "        \"end_x\": 445,\n" +
            "        \"start_y\": 1035,\n" +
            "        \"end_y\": 1076\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":3,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 2059,\n" +
            "        \"end_x\": 2139,\n" +
            "        \"start_y\": 755,\n" +
            "        \"end_y\": 797\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":4,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 1585,\n" +
            "        \"end_x\": 1665,\n" +
            "        \"start_y\": 489,\n" +
            "        \"end_y\": 530\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":5,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 762,\n" +
            "        \"end_x\": 841,\n" +
            "        \"start_y\": 664,\n" +
            "        \"end_y\": 705\n" +
            "        }, {\n" +
            "        \"start_x\": 1887,\n" +
            "        \"end_x\": 1966,\n" +
            "        \"start_y\": 626,\n" +
            "        \"end_y\": 667\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":6,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 1874,\n" +
            "        \"end_x\": 1952,\n" +
            "        \"start_y\": 746,\n" +
            "        \"end_y\": 787\n" +
            "        }, {\n" +
            "        \"start_x\": 749,\n" +
            "        \"end_x\": 827,\n" +
            "        \"start_y\": 783,\n" +
            "        \"end_y\": 825\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":7,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 1104,\n" +
            "        \"end_x\": 1184,\n" +
            "        \"start_y\": 1497,\n" +
            "        \"end_y\": 1539\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":8,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 1156,\n" +
            "        \"end_x\": 1235,\n" +
            "        \"start_y\": 232,\n" +
            "        \"end_y\": 273\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":9,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 1351,\n" +
            "        \"end_x\": 1430,\n" +
            "        \"start_y\": 345,\n" +
            "        \"end_y\": 387\n" +
            "        }, {\n" +
            "        \"start_x\": 932,\n" +
            "        \"end_x\": 1011,\n" +
            "        \"start_y\": 1394,\n" +
            "        \"end_y\": 1436\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":10,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 910,\n" +
            "        \"end_x\": 989,\n" +
            "        \"start_y\": 90,\n" +
            "        \"end_y\": 132\n" +
            "        }, {\n" +
            "        \"start_x\": 1244,\n" +
            "        \"end_x\": 1323,\n" +
            "        \"start_y\": 934,\n" +
            "        \"end_y\": 975\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":11,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 224,\n" +
            "        \"end_x\": 297,\n" +
            "        \"start_y\": 560,\n" +
            "        \"end_y\": 601\n" +
            "        }, {\n" +
            "        \"start_x\": 2025,\n" +
            "        \"end_x\": 2098,\n" +
            "        \"start_y\": 1266,\n" +
            "        \"end_y\": 1307\n" +
            "        }]\n" +
            "        }]\n" +
            "        },\n" +
            "        {\n" +
            "         \"part_id\":12,\n" +
            "        \"illustrations\": [{\n" +
            "        \"illustration_id\": 192347,\n" +
            "        \"positions\": [{\n" +
            "        \"start_x\": 716,\n" +
            "        \"end_x\": 789,\n" +
            "        \"start_y\": 1270,\n" +
            "        \"end_y\": 1311\n" +
            "        }]\n" +
            "        }]\n" +
            "        }\n" +
            "        ]"
}