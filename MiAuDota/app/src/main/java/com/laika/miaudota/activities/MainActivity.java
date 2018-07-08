package com.laika.miaudota.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laika.miaudota.adapters.RecyclerViewAdapter;
import com.laika.miaudota.R;
import com.laika.miaudota.models.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMSEhUTExIWFhUXFxcZGBcWGBgXGBgaFRUXGBgXGhgYHSggHxslGxUWITEiJSkrLi4uGCAzODMtNygtLisBCgoKDg0OGhAQGy0fHyUrKy0tLS0tLS0tKy0tLS0tLS0tLSstLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tK//AABEIAMEBBQMBIgACEQEDEQH/xAAcAAEAAQUBAQAAAAAAAAAAAAAABQIDBAYHAQj/xABEEAABAwIDBAcEBgkEAQUAAAABAAIDBBESITEFBkFhBxNRcYGRoSKx0fAyQnKSweEUIzRSYnOCorIzs8LxYxUkNUNT/8QAGQEBAAMBAQAAAAAAAAAAAAAAAAIDBAEF/8QAJhEAAgICAgEEAwADAAAAAAAAAAECEQMhEjFBBBMyUUJhcRQiUv/aAAwDAQACEQMRAD8A7iiIgCIiAIiIAiKH3j2n1LAGn235DkB9J34eK43Ss7FNukS5ctP23veQSymAJGRkdm3+kce8+q0bbG8M7pmwsmeWudbDiJyzJcbnTJSAiDclnnmfSNeP06/LZj7Q2lWPN3VMncHYR5NsFr1dJM45vc7vJKna9xtbQKIqX4Wqjm/Jp9tLpETBtWeJ3sSOaeRI9ymNm791kbh+uc7+F/tA8s1BYQ99uKtVlMW5gfNlNTaK3BM7juhvpHWfq3gRy9l8n/Zvx5e9bWCvmBtUWkEEgixBBsR5Ld90+k2ZkjIJz1jSQA51y+xOhN8/FXxy/Zmnh/5O0oqY3AgEZg5jxVSuM4REQBERAEREAREQBERAEREAREQBERAEREAREQBEVE0ga0ucbAC5J4AaoDC2zteKmjL5HW7Gj6Tj2ALk+09uSTvdI92g+iNGjgAsffPbhnlc/PCXYWDsaPm/ioGSX2Xi+rHX8B+azZJctG3DBR2+zzdOq6yrlkd9VoDeWI/Bq3CebiuXbq1pZK8XycAT4X+K3MbVa1he7PC0kjkOKpknyovg1xsk5qpoGY9RZR20gMGIceGvuWvbU3keYo5urYI5nSNaBJeQdWQCXMtZouQs2g/WMx3OY0vcan4Lk8Uoq2IZoy0jEp2nrmW4kj0U/UUYeLHI2UTIzBIw8A8Ee5X9o1uF4F9Qov8ARJfsg66lLXBuJt+y/oo2qlMb2O0IPuzVLqm0pAGJ5JtdwaMrnU9ytbTqOsijeRa5Nr8W9vm0haIxkZZTi7SPo/oz23+kUpaTd0L3Rntw6s/tIH9K3BfMu6+9clDO6SPO5ZiadHNtZw8bjxC+jNi7TZUwRzx/Re29jqDoQeYNwr4StUZ8kadmciIplYREQBERAEREAREQBERAEREAREQBERAEREAWpdIW0+rhEQPtSa/ZbmfM2HmttXHekaqP6fI0nJojA7sDXe9xUMjpFuGNyNSrHEuIPbfz/wC1T9XTkrcs3tX7MgsijPDUaef5rPJ6NsVstybothpKauie44nvima7g4OeGubb6pDRke0KUotnB7HtOV24T45LDr9rEUb6X/zdY08zG4EW/pb5rO3f2gC4EiweACOZtn5qvI+mjuJdxZpNfupKHWGEnQE3bcDjYArcNk7LFPCGveDhBJ4DM3y9clJysOPDfK+St7Qa0jD2BSeZyVHI4FB2QG1iQ1pt9a47r3Cj9vv/AFrXcLKfr4w5gbfTldavtgE2HEZKESUyBqoMT72uDwvZbVt3cuaPZ1PXFzcLnYer0LQ9wawjtBsSfA53ytbtbIdUVEUTfpPdbPQX1J7gCfBdB6bajqKWiomm4+kTxtAwRtv3l5P9K1RlaMco1LXk5Bju53M/jku19Cm3mlstK9/tB2KME63BxNH3b25lcQbkVnUda+GVsrCQ5jw8EdrTdcTpnZK1R9borcD8TQ7tAPmLq4rzMEREAREQBERAEREAREQBERAEREAREQBERAFxfpMb/wC/ksBfCw+OBo+e5doXHOlO36YfsMvz9k/AKrL8S/0/zNCeLkdt1d662h+QVTO7XJWWgutYXJsABxJ4AeIWXs3Kkeba9mISn68uFvdGAXejwpHd2PE0txWIPsn1CyelLZX6K2hpuLIXvd/MkeS/yIA7gFru7ldheGnjl2dynONIphO5WdHmda1xz8eKjJb4yb+HgpjZzjM1wda7bWPb+eSiJ4T1hHifBZVpmws1kWR5DgtW2pnZbNUVQsQ7LvWp1EwJe3iDcdi0446MuSWzdeimic/aEbwPZYx73H+ks97grnT60/pVMeBhcB3iQ397Vi9Fu8rKOWTrGkse3UatsQb2Ootr9lXumfbUNYynkiDsUTntJNs2yhp072N81dGuNGeSfO/By94WXA3EAfNYRfkr1NPhKjsno+t9jy44IXfvRsPmwFZd1xePpLnhhhhY2P2Yo7OIJJ9ga52usSbpTrDkHNb3Mbf+66tWRUUPFKzud0uuAS9IVcc/0g+AaPQBeU/SBXg/tDj34Xe8J7iHtSPoFFw6n6S64avae9jfwspGk6V6gWD4Y3c/aaT+Hou80c9qR2BFpexekammylBgd/F7TfvAX8wttpquORuKN7Xt7WkOHopJpkHFrsvoiLpwIiIAiIgCIiAIiIAiIgC0vpF3XNUwSxC8rBYtGr262H8QOneeS3RFxpNUyUZOLtHzTU7ImLsAY7HphwnFf7NrrpfRvuAYC2pqh+sGbIz9Qn6zv4uwcO/TpOHO69JVccSiWTzOSo4f042fVxgasjDT4lzvxC53sqH9YMtCt63/AH9dVSSEZYvQeyPCwWt7Op8Ml7ZLPKd2ao46SN22fI1kbnOda1jfSyrqJWyRiZmYI1UbSgOu5/8ApsFzfloOeau0leGEtLThfdx5H/qyoo0cvs1HbzyHtHA/J+eag9pxvifZzbE5jhkeS2neiMPb7Ficy08OGV+1ahLRuecy95AGoIPMWudDdbsVcDz8983RObvTDqsWjmSOBPY3AHDLwepaugZIwNyAe027MrHzB07gtapwIYXNJHWPyIHAcb+o8e9ZstTaBgB9oPuO3IKmb/3Lsd8KZrlVA6Nxa4WI9R2jkqoASbAXJW0SBszBdoJ4X49rbjQjh3qHkwtvgaAdCTcnnqcvJS5EeJlSyggDUNaG/dFr+dz4q1cHj7vJYTXkKsvQ7ZdLrKtk6xi+6oL0QJEzc1UKvn4qND1UHLtHLJWOqUns3b0sLw+OQsI4tNj3LWmPVwv5pQs+gNx9+21VopiGy5WOgf8AB3vW8r5No60sIIXc+jXe01DRBKbvaLtcdSAPontI9wKthLwymcPKN/REVhSEREAREQBERAEREAREQBRG8u1RTxX+s4hrRzOp8Bn5KXXMN+dpdZV4PqxDD4mxcfcPBV5Z8Y2W4Yc50Qm3qW5DuGvx/BQNBSHrHDTyzH/S2iGcEYXi9tDy+SotjC2cAWsRe/u9PcvNuj1aMmWFoEbOeIj7OhPjZYVeRe/I5crhZz5AXON+Nh3N/O6wKoguHr6fBS5EKNfrG2BsSNePYtfrJX3tiNuwH4LYdt1QaLcfn4KFZCXOv6qcXRXNeDDZFbMr3GSsupgWK5tlJOyDVGZQz4fZOh0PYV7tGLEMX1vrc+aw7rO629x2getl2xRDFVMKSNXjQrCuj1eFVrzCuWdopaqwF6GL2y7Yo9aqzmrd1U0rtnD1TW7O1HwSh7TYjMHmMx7lBkq/E62i6D6e3T2+ytgErcjo9v7rhr4cQppct6DX3bUj+Uf9xdSV0XaMs1ToIiKREIiIAiIgCIiAIiIDwrh20Hl8z39r3X7ySu5FcMqYz1ju88eay+p6Rr9J2y2+QjyVEUh6y57vP81U8fPzxVdPD2a9qxHoF0R5+vmFhuZnnlw8M1OupfYB1+QoWvGH2ictc+3NKOGmbYJLyL6H5/FZ1BDZtyox78chJ7fxU1GCW/PuVr0ilbZF1LsTjbRYksWqluptc+nz4LCquAHkupnJIw7ZBXA6z/Ae6yqdbJWGn2/FSIlh4zK8Y1X3x5lA1Tsi0UYV7ZXC1etYuWKKA1eYFkBqofGbJZ2jFGqrCqGSpuukSlo4q/g0VkGyyoRci+i7YR2boSgsyoP8of5k+9dPWl9E9B1dHj4yPLvBtmj1BW6LRj+KMuV3JhERTKwiIgCIiAIiIAiIgC4/tylMdRKw3+mbdxNx6ELsBWjdIWyvo1LeFmv7vqu/DxCo9RG4/wANHpp8Z0/Jo0zACP8ApZ1HGHC/irbrXusgEMYMP1vRYGemjMleCwDuWpbyy2a7y9wWyVAIYwcdPf8APgtV3hpXO17dF1EZI1imgu6/BTbSAAOJ87qxsiI55KREbWZ+KnJkIosR0jjnkOfwCjaiEB6knVdwo+rkvn7lFHX0YEupWPT2LuaqqH2SgZY4jwVvgp8lyoZYlUWVyUkm/agYh2ikMC9AS11N7L2Pf2n5BAYEFGSPn57Eq6SzVN1lYyP2Q29uCxHVjZGkAYTa9te/NKFo1eS6tNKuVbsyrAN1NdEGXI8ys6n1ViFllmUwzaO2wudNVw6lR9L7o0/V0dO06iJh+8MX4qXVqmjDWtaNA0DyFldWxdGBu2ERF04EREAREQBERAEREAURvbEXUc4bqGF33CHW9FLqiVgcCDoRY9xyK41ao6nTs4dFU4gLjl6LMpXXBYfD3rAr6J0E00DtWEgHla7XeIIKpo9ohz2t0eWYmj94fWH2mm4Xm8dnrKWiT2hVYWgi9wompnD234lSlRKHCyicIAt2LvE7dmLFHa6w6ubEMu71upOdl23Cw5oBopOJGzAib4LFlPslzdL28jZZO1ZHMjcWkAga8VHMlIhbfiSTzvmlasg5boxHDiVlUrbg+H4/BYzn3W1btbuzVLR1cbncSQMhw104FS2R0QQhJVbaZzjYBdJ2Z0cTvyfaMXIJdYk5kZAcFuOyNwKWHNwMjv4vo/d+KksUmQllgjkeydjHI4S43AyF83GwHeSpTeKjmpCGSMLXObcHUW5EZEjj2Lt8FIxjQ1rGhotlYWuMwe+6s7U2VDUMwTRte29xfge0EZg9ys9il2Vf5G+tHzPNLYnIk81MbJ3JrqiN1SxjWMYC4GUuZjABJwANJOmpsM9V3TZ+69HCbx08YP7xGJ33nXKkq6MOjeDoWOB7i0hSWL7OPN9HyhtGkN8Q+icwsWNttVkuqntc5od7OImxz9Ctj2JuLVVtK6qpw02kczqr2Lg0C7mk5akixtproqUmy5yS2zW2FXGn571arIHxPLJGlrmkgg6gg2I814HXXCVn0h0e7wMqaWNpfeWNoa8E+0cItj5g2W1L5RoNqy072yRPc0g5Eaj8l9D7gbymupsbrdYw4X2yBNgQ4DmD5grRjnejLlx1tGzoiK0pCIiAIiIAiIgCIiAIiIDlfSfSFlXHKNJYyD9qM/AtXM697oyXNNnRu6yPxvjb3Hs5ldh6XY7QwSfuylvg9hP/AAC5LvDHibdtvyWLIqyM9DE7xE7SbRbPG2QAtxduWYycLnsKtzO14fgt86MdiQzbIjZLGCHPlce1rsZbcHUGzR+ao2n0ePbfqJA8fuyZO7sQyPou+01tBZ4t09HPnTm1ljGrz+K2uXcqsORg8Q5tvesaXo7rScorc8TPfdR4SJPJD7ND25UmQtjbxPtdw/NWK92QaFJbU2M+lqJGSWMjCAbZj6Idb1UPKbko/oj+/sv7Lo3SyMiYLve5rQObiAPUr6h2DsttLTxQN0YwAm1sRAzceZNyuOdDG7j5ZxWEtEcLnNt9ZzyzLK2gD73v2LuYWjFHVmbNK3QREVxQEREAULvjtAU9FPIeEbgPtPGEerlNFcR6Yt8hI80UeTWE43Hi4DOw5ZjxKhOVInCNs5fKcyV3boLqGuoZGjVs7r5/vMZY+h8l89YjfVd16AaB7YKic5Mkc1rOfV4sR8328Cq4KpF2R3EgumzYhhmdUNHsTAE5ZCQWDu4kWPPNcpY4ldp6fN5IxGyhbZ0hcJJOOBrQcA5OcTfuHNcYpm8SuSSTZ2DbSMtjT7I17tSexfSPRpu26ipA2T/VkON4/dyAazvAGfMlc26G6Ok/Si6d4/SQP1MThkL5lwJyMltBwB7dO6hSxx8kM0vxPURFcUBERAEREAREQBERAERUTShrS5xAAFyToAOKA510xVzurhgaMnOL3Hsw5NH9x8lyyucBlcqe6QdvmedzgSBkGj91jdB3k3J71phmJyJJWGcuUrPRxx4Qo7j0Lyk0TwTcNmeG8gWtJA8ST4rf1rfR7sY0lDFG4WeQXvHEOeb2PMNwt8Fsi2QVRRgm7k2F4V6hUiJwbpSbH/6jLY5lrMQ/iwD8LLQpo7HMEZ6FbZv5U9ZtGd1sseH7ns3/ALbqA2nF9Ei9vgsDlcmelGNRR2LoNZahl51Dv9uNdHWkdDtPh2ax1x7ckrsvt4B6MC3dbYfFGDJ8mERFIgEREBjbRmcyJ7mMxua1xa24GIgZC5XyNturdJUSvcblz3Z95v8ABfYEjbgjtFlyDeHcaFr3O6lhz1wi6i42SjKjiNLE5zrDnc8AGi5J5WXYd0ukuk2fs2OBrJJJmB5sQGMLnvc/N9zYZ9l1zbbWNspiMTYmg5NFruHBxI17baDvzUTLcHDlYnU5Kt2notSTWy/tOsknldLIcT3kuJPEk3JWy9HG7wrasMkJbFG0ySuyyAyaLkWuXEeAK1mFlrX8QuudE2xpBTySPbh69zSARYljAcJN+BLnW5WPFcS5OiTaUbRi7F3Ew7SifTyySQsla9xkADyQ65zblbThc5ruqwNl0DYm5BZ6vqjO5WEREOBERAEREAREQBEUPvXtWSlpZZo4+se0ZNuGi5NgSTwudBr6gC7t3bsFIzHPJhB0yJJ7gM1zDfXf9sowMdaM9gOduLjb0071zzaG26urmx1OJzyDa4wsaB2N7PnNRPXvLhGQb3y7xn5ZLLkc5OvBsxRhFcn2SG0KzEbHXnksjc+Drq2njsPalZkewOBPoCoipJdmRhI+kTplxXR+jDcaaSalrnHDA0veODnFuER88LiXHTSPX2goY4WyzLkpHcgvV4F6tp54Udt7araWCSZ+YY0m3Fx+q0d5UiuOdNu2XGWKmYTZjHSv7NMhbtsP7u9Rm2lonCKcqZoG0NpvkmdI5pvI5zstMyScuzNeCqjeMLnWcDlc2Wvx7RcXgA6iw73EfBW+vIluRbK3LNY/bZveVUfRHQxVh9C5lrdXM8fes8f5W8Fv65d0D/s9Tf8A/VuXZ7C6iteP4owZPmwiIpkAvF6vEAUbtalDmnJSStzNuCuoHH9s7oxzSYnsDjwOhA7wsNvR9Ef/AKQe8k+8rqL6MX0WdT0gtoucSXI5NDuVCwgiFlxyv71vm6kGABpGQ05DsCkp6QXVyjgsUS2GyYC9VLSqkIhERAEREAREQBERAFC75fsNT/Kf7kRAfO1B/qyfZ/5lYUH7U3+Wfe9EVK7f8NL6X9LU30/6vxC+ldw/2Cn+z/yKIpYuiGbs2BERWFIXDOlX/wCWH8tnuciLkuicOzm9HpTdz/8ANWKv6bu9v+SIqfyX8LfxZ3joR/0Kn+cP8AulBEVmP4lWT5M8XqIpkAvCiIAqX6IiAjnarLp9ERdBjS6qqFEQGcxVIi4AiIgCIiA//9k=";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Animal> listaAnimal;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAnimal = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest(){

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response){

                JSONObject jsonObject = null;

                for(int i=0; i<response.length(); i++){

                    try{

                        jsonObject = response.getJSONObject(i);
                        Animal animal  = new Animal();
                        animal.setNome(jsonObject.getString("name"));
                        animal.setSexo(jsonObject.getString("sexo"));
                        animal.setPelagem(jsonObject.getString("pelagem"));
                        animal.setDescricao(jsonObject.getString("descricao"));
                        animal.setEndereco(jsonObject.getString("endereco"));
                        animal.setFoto_url(jsonObject.getString("foto_url"));
                        animal.setIdade(jsonObject.getInt("idade"));
                        animal.setPeso(jsonObject.getDouble("peso"));
                        animal.setVermifugado(jsonObject.getBoolean("vermifugado"));
                        animal.setVacinado(jsonObject.getBoolean("vacinado"));
                        listaAnimal.add(animal);

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(listaAnimal);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Animal> listaAnimal){

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, listaAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }



}
