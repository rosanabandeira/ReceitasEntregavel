package br.com.digitalhouse.receitas.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.receitas.MainActivity;
import br.com.digitalhouse.receitas.R;
import br.com.digitalhouse.receitas.adapters.RecyclerRecipesAdapater;
import br.com.digitalhouse.receitas.interfaces.ClickFragment;
import br.com.digitalhouse.receitas.interfaces.RecyclerViewOnItemClickListener;
import br.com.digitalhouse.receitas.model.Recipes;


public class RecipesFragment extends Fragment implements RecyclerViewOnItemClickListener {


    private ClickFragment listener;

    public RecipesFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ClickFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("A activity deve implementar o ClickFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerRecipesAdapater adapter = new RecyclerRecipesAdapater(getRecipesList(), this);
        SearchView searchView = view.findViewById(R.id.search_filter);
        EditText searchEdit = view.findViewById(R.id.search_edit);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        setSearchView(searchView);
        setSearchEdit(searchEdit);


        return view;
    }

    private void setSearchEdit(EditText searchEdit) {
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("LOG", "Texto digitado ao digitar no EditText: " + s.toString());

            }
        });
    }

    private void setSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("LOG", "Texto digitado do searchView ao submeter: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("LOG", "Texto digitado do searchView ao digitar: " + newText);
                return false;
            }
        });
    }

    private List<Recipes> getRecipesList() {
        List<Recipes> recipesList = new ArrayList<>();

        recipesList.add(new Recipes("https://abrilmdemulher.files.wordpress.com/2016/09/receita-salada-carolina.jpg?quality=90&strip=info&w=620&h=372&crop=1", "Saladas"));
        recipesList.add(new Recipes("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUTExIWExUWFxgYGBgXGBcYGhgYFRUXFxgXFRYYHSggGB0lHRcWITEhJSkrLi4uFx8zODMsNygtLysBCgoKDg0OGxAQGy4lICUtLS0vLy0tLS0tLSstLS0tLS0tLystLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIALcBEwMBIgACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAEBQMGAAECBwj/xAA/EAABAwMDAgMFBQcDBAIDAAABAgMRAAQhBRIxQVEGImETMnGBkUJSocHRBxQVI2Kx8HKS4RYzgvEXoiRD0v/EABoBAAMBAQEBAAAAAAAAAAAAAAECAwQABQb/xAAuEQACAgEDAwEHBAMBAAAAAAAAAQIRAxIhMQQTQVEUImGBkaHwMnGx8VLB0UL/2gAMAwEAAhEDEQA/ALy7oscUsuLJaT3q13l0EjIqn6hrsLjmvJlhgerFtnVreuMK3J+lOGPF7hIBQPrVeOpJWOlRNujcDxmhHXj/AEvYWUIy5Rf2tTUocCpEvKPBj5UosXAU0U29Br0FvuYWqJri2cIncaXKsFGetPmHpFbs0gzQ02CyrOWSu1DKtlVeF2wPShnNPSelK8bCpFONqajVZGrcvThUStP9KXQHUVNVia5bte9Ws2PpSq7Y2mmjGgNgjbQFTJTXCanbFVRwfpl2UYjFMXbtC8KFC6dazW7tnaqmjBSdMW6doxaWfuj6UI4xbkyUA/Ku3higUOGYppdEvFFFml6hHtbdBlKAPgKhF4yDITn4V2bYGoX7BNJ7I/FHd0xzUW/uVpWrj7tDiyFdrskxRXSSA8iZ3/GFdAK1++rPUUPb24mmFswmm9ka8g1oG9ss/aqBx6OVUzet+wpFeMkKNZ5x0uhk7JC/W0pJoVqj7ZVIkEkYtprHLYCiLU1lxXo4ccHFOiTbsHS2KMtwKEBolk1o0pcC2FzWVxNboADddsiYjFVl7REGSeaYXmtLXwIHSly7hR5NeLpUtzYsjiqIrXSkJmc1N+4I7VwHKnQ5RUEI8smE2gjFGOGorNqc1I/V1wTCrF/MUysFeYiq9buZp7Y4O7pQugNWNYrRTSV7xM2FlAyRzXTOvpJyMd6l7Zh16NW476fIlbQZf3KWk7lcV0w8haQoHBpbqLqHMlXlHSorK4bW2r2ZiJFJLNLVsthli90nv9USiYExSpGoNvZ4rzO98dP2ty404lLjZJgjkfrSx7x2AsbcJmoZZ5nKOjjya4YMajvyeytW7SlbQc/GiEaYQfSvLfCXidar0L3ShSYjtxFerpv1K4FaIZGZ8kEhjaoCRUN/b7iCCKjbUeooxLIiZq0ckr2IuK8i5ywVFIbvyLzVgVfhKtvND3mnJdMmnXWTsbtLyAM3KT1FdPGgrvwikq3JcWk+h/Ko0suMYcXvT361fH1UW6lsK8XoFBNYs4o5iz3iUmuv4OrvWjuR9SVCNlWaYW5zRKPD5mZo1jRorpZonUbtGQoUt1KyEmrEzbBAoC/TWDJvKxkU59mK6tqL1BPNA26s1Ktxxjb+9Uj7Kjwk13pC0+0E1bwyntWvFl0qhJLcpCbNz7tEs2TnargGk9q2EjtVHnYpWBYudq1Vpispe9I4pq0BtMvgJ+PAqe3Zt1DcCkzSp62e1BBDrRQk8A4+tD6R+zt5KocuVBscJT27TWDHbWyNMklyyw/w5k8RQmr6KW0b0/SnNtb29on3pI7mTXI1JDrgCvKkZAOJNUaS5J7vgj0jTl7ATie9SO6Ks/aFF32u27I87qRHSaQL/aHb/ZQ4od9pj61zyY47NhjiyS4RvVLI26C4oyBQa9alryHntQXijxGq6ZLbTZz3qs+F732Du18gRwDWLLlWSWmPBsx4XGGqS3G19p1wRhJTu+11rly7Uw1tBKiOT1p3qWpuvFHsUSick4x6Uj8Warb27cuI2qPYdazQ6WMKp3XBZZXJ3JANvqL7pCUJMTmjdY19NgwQpQC18D1IqhM/tM9gD7Jrco9TgfOk7Ok6jq7pd2kzwo+VAHZNbseCnctiObqU/digS7Q5dvDar3jlR9TTT/45uFvJbbUFJIkrPA/Wrl4V/ZE9INw7tAPuoPPxUa9Mt9Hbt0hKTn8as5uK2IvTJle8K+B2rRCQfOvqr1q57CANgFeReIvHr9rfKawUCOfXtRv/AMkoXjftxWdzremU7TZ6P+8palTzo+ExQl14iC0n2WYryi71pL7gQhRdWo4k1fvBmhLblTxkngdBQUpS2WwzhCKtu2M7ZBICj7xps1cqSMipCUDtjn0Heg1LT5SVTu4FFOMHVkpS1sH1O+WUnYmD61VtbQ8tvcte0dhVk1h15SSGNpWIjIyOCRP96gsNOU+km6BQRyERC/UAYSfhz2FRnNTdJjwaitTKt4N1K7J2pJUgGATXqtmtW0buaT2tuzbNwhOYwMT6Vjd24YnmMx39BW7G9KSbMuR6naH+6te0FJTcKqVsk8mrWToaqXQmoMgJmaidugBzS+4vfWlbOoX37c0nUiDTd+4BoB1QpGMiNp0ggjpVu0vWUqACsGqfuFYHflRU0jmrPQ/3tPcVwq8T3qiJuVfeP1qZDp7k0e4DSXL9/T3rKqW71rKOo6ho94n6IR9aUXuuvK+1HoKFs2ypcK8tWS1t2EZMT61NScvIaoX+F7QLVveBJnG7/mmfi23aWzEDdIiOfwqe8bG2UcVT1asfblBgR36zSZX7umuSuJe9d8HY0tJblKNxHQj9aHXY3LqdiWkt/HNMxrLiDlKQPjXN34qDWViJrL24o1qcvBJa+HnQ3tW4Ae4AH96RXPhtoOjcrdHJnJrm98TqclUwiMGaq1j4hhzcNzxKuBmKlkipUorgrCc43bL894iaYhqMAdBSLxLpKdSahteT17V21pC7t32rsNojCep+NWqxtbe2TCSlNXhCXLZOWWKVRW5UPD37MLRnaXR7ZYzKuJ9BxXodhaIQAEJCQOgpW9rdsD74PzqAeKGyYBx3q0pJvfkyaWi1qukpGTVU8SamQpOzqaD1TU1LQS3Kj2AmlDbly4oK/d1SOJgD8TU8mqSpFcSUXqEvi3wg7eObkIhX3zj/AN0LYfsnSnNxc/EJIH1Jq8NN6gvB9m0PQkn+1dXnhwrUVOXBicARgdKZLIvdj9wznFvd/QUWHgqytiHUIWopyFAqV88UyPi5CyG207/piPU8UbZ6aw0MFRjsVfrXD+phJO1AEciSD3HmTntWXqMzxbTdt+i/n+xYyT4RLZOLI8wUkqBOcmIx/qz8setSm6JVuSoBCiFLSeuzHlnj3RIPrzVO/jSkvDdvj3ZBKjJiDnJ+HrTp1reN5J8wORie/wAJgCR+NYZSb94Vu3uPlXzaleRIBPpieI+PERXVrcKkg9Z69uPh/wA1WUuAIbJmFKKemUhCIAnnk9R7tNbS7kzkbeMgyJ/L9Km8zUk2gVsGFe8nrnBjqJ4kRgwfpR1lcbkJVuVO0fd6mOIiaVFTsrSMIgbOiAkkzOcKExjmDRtnnlW4kAE9AARhP05rVjyuPO/z2+ArQY4Tu3ERx9B/7P1qN66PAqN3xA0SUKwpJKT8QYoV67QfdVXtJpLYkaedPU0A/eJHJrHmUq5cPyqNOnsdZV8SaVuXgZJAT+rp6ZoU6g4r3UH6U/btmU8IH0qb24HCQKTTJ+Q3FFebtLlfSPjRzOjKHvuR8KNcuz3ihHXu5ru36naiYtNI6lRod68j3RFDuu0DcXFNSXAOSdV2Z96soFInNZXHUW+60R4qJB2yelaT4ccPKiasL2ro7ih160nvRUUgWybT7VSEhKzIpbq3h1h0yrynuMGpV6qD1rSLhKutHY5WLtR0AbAhLh+PWlF34OcdR5nxj0q3Jtt1YvRd3Uil0J+BlkkuGeaXX7PlrTsN4Up7AD9a3on7PUW5JF2TPomvRFeF0feP1oZzw0gfbP1o6dqo7uSu73K7/AUzKrtwx0CgB+AqT+E232lKX8VKP504OiNj7R+taGmtjrQ0peAvJJ+Rc1Y2ieGgfiBRba2k+60B8h+lTewbFZKB0o7Cttmk3UcACt/viu8Ui1W7AX5TWW4dV7qFH5Gu1UChz+8kmNxqdIxBM/l8KGtNCfncrakepk/hTRvR3MFK045yr9Kz5cy4Sv8APqOoC9ZHAUE45Ofw6/5xSG9vE+02qyDxPOQADj4zTi+fO87QHAmRKSgqOTnziFDHaq5fLK3PaBDoG5CTKTxgGdoiOfSvOzNZGn6FYRoFVp61ISr2kLxCVLCCUKTykkiSDmP7zUo1Flta7eFOrIT7RWQUhCNm1r7xypSlDmAMxW33kuOoAUApATKVQMbTCgeDjkCTlNB2TYN44skK9kpyIiCtUqKf6oBI+Pwro8OzmiwNORDa5UmNyFmPeB4M8GDyPWprN5KZ2yEgZIEgGftp6fGqX/GtjhaUvelUmTnbJxjp6imNs86D5fKQRImMdSOxj/O+XJja3YNi4uH2sQryA/ZJgpJnI/L40YynYkbSo5JAOY7QYmP06VXtAuDKlKSUwDu6A4njgHrzTXSGkm4SlThUkD2hSoctkeTr0JAM8xRhBzl9gXQ1OisOEuKTClGT5jknJIz1mal/gbA6R81frUAHvAw4nMAjjM84olltKgM+9xJAT8BE5+fSti6mTfwO0o1/CLciYGeyj+tdp0dn7v4n9aEuWoO1MTEZzB+Wdp/CpnHVNoTKkdjk7QfnmmXUT3vx8TtJMdJa6f3P61A7pA5SCodt1AXGvFvaAEvLUqIAhKAeNyv8Ipo+tQUdiTEAnJIGO56c0V1EmrTDprlAitEBOd6Z4Agx8zS648POzhYjuQof2mnlvcuKnccHiOg+NGIexgjA5Ofzp49RNq7A4r0PNtXtLhnKmyocSjzie3lyPmKrS9RO6CCCOhBB+hr1rW/E1rbAe1UCSraQmDBxM9oBmKIu9NZeAV5HR9ncAsEHsek+lVXUNcqwaUeTpvVVleg/9LWwwWFE+i3I+VZT+2YvxB7cha7cpFDKvR2owaZUrejE9PrU3lyPgGmKFKtRP3aIs9Wg5QacJ0RI95QFdllhAwNxqkVlYr0jLTL5OzdU41iRiqfqF2T5RgHtUwfhIFaNTEofXGrml7upE9aUuP0M5c+tDUNQ3Xe1ybruYoCwt3XjDaCe6jhInuevwFWO08MoEF5RcPYSE/QZNTlkjHkNCUOuLMNNqWe8Y+pxTu28NLP/AHHD8E4/E5p+hlAA2piOOnStlZHP4H+81GWZnbATGjtNmUtjdEbok57k5om3bUhMrAMCSR6DMUQLnEkVWLvXi4ohCHBtlJJAAJ/pE5+JxUsmSMFq5CtT2HFjqSHRKY5IA44Ejnn40v1fUk7FpQ4tCikgKQOD0IMY+MUpfLiG9qGCtSU4KzBVjBJ9TihTc+8CfYuKwpIUlRxgAyOPTFQjndb8/QbSQWuqNjLoWTkmCDuM84iPnSnXXi4kPsrKBuCF5EIzMn1IipNTsHSApLkx1SIP+0c/jSzw8UJccLj6VtFBQtCgRuE8R0IM855FJjuX6h68kre4OuOrSFJaA94Aq3ycTE4CZ5+1Qej6iD5l7doaW8soTHmWQlJAHJk9am8Q6nL3sWEBal5XzMBJSD2nJqsOagLdSbZLZkwp5RwAACdiT91P9/lVIYtXj+lyCb2J3judKlJD6mhBMGVrEFAKRiCFAk/0mmdjeKhS3FJCwPcK0glSsAE5j49BJ6UgttaVcbmmWlIEQClO48YkDjij7YMsI8vvpV5nCA4UqgeXEJ38mOkiZOKfJidVNfL82XyJ6vQt2j3hTb3DilhwDACTgK2iR/Tz8e+asumsJ9iCVGVJDc8koQorV+JCfSKoDV+hNosJSpZ3hZKlBRWpUqknAHHGIirFcao241btNKWpYSnej/thZgbgCoQFySQODBAzAOdQak6X5QadFhYuG3WyiBuR9ndJUmTHwV6etTMoUiCkkdxPl5JJjqarVjp1wtQPtPZlK3DAUknYEACQE+8CY+Znii7WxulBAdXuJOfjORKcY9YqOSDjuufgNGXhlj/fN5SkExPmI6gdI6ZHNMHG0Hz+zAB+1EEzjoc9K1plqhAkCTEE8k+hP5CKLVqLBOz2qJ4ACoPwFacWJ177Fct9ha9dITI9ipSSQkwkHmM4yKD1K+S0AhaXghWAoNKUAJnz89vSrD7BtSsEyn+pUcdcwfnQzzDSgQlUHuhcH5dJqssTa8ATYjVqKEtJCUOKGQNrKiOcQAnj41LYLKgtse3bPMltO3k+UEiJ4+lNf4WhI3e2djruXPT0iKDWgIUFreX7MckQB6SSJzUJwnFq/wAQyaoh/wCn2lIWFJHnWFGQmSUjBSemSfrTW1s0NoS0kEJAwJmPmcmpk7HMjOMGa5LaokTHfH4TWqMGlSVoU37P+pQ+n/8ANaqXafuK/wBwrKPaf5Z1kb2iY8rkfEVXXX1AkFXBjFB3XiB1fvLgdhS13UQOTXotx8ElY2ce9aXXl6AOaR33iBI61XbrXCtUCYpbDRY/4ikqknisc1lPekLB3fZNFoa/ppGMEOav0AJJwAOpPQDrV48P+GYCXLgBSyJDZ91E/f8AvH04HrSTwaWm1OvLRJbCAnAOXFxIHcRP1q+J9oVgHAOdySenAms2XLpelDpHNtaIblXBUTuI8oyZ6UUw9uJ5gcE9fUHmKjff2gAqhMxuMnPMYri7cbbUPaObfn+dZO5XC2+gzd8hIWd+YJiO0Z/Gpy4OB9aTPX7BylZIH3BIP+pUUIrW0pc2qMz6jyieoEx86KzJOnX8iabGWqlShA4Prjpz+vxpSVG3G51afZkeYpJKgYAGSPd5+E1NqGutIHVf+hJIzx5uKSO680sqTsVwcSM9MCunKKd2PHiisX+vPF5SSveCQZCoBR0G4GQYIHP50QHm4wAFRgAlQCT2A940anaFeW2DPJKlgn0lKBE5jrRxDSoKQmT3xMcwKi46tyrnF8FaeuloG5GB1B5Prj86nZ0kHzrQpO6FgEJwtQ99PUGMRA681adF0pp24Tu95IKkiRBKTE7esTz6Ut1ezdU+sEw2kkAgkFRBjAjj1mjpcceoGreipnQ0oWo/vbze8ypJQkyYIAJ56nr1ps5orxKlAoV7NpKU7gBvcHvqcJQSkTOZM4p07pQUpK1Jk+QknumJz8qY+yc86ykBKgEgJAMnEjjIiZnuO9L3n5/jx8gNI81uGNVUlSQUwMBLam4I6yBBPwilx8K3izKkrbTEqO0hKe5jma9SVp6Uq3BkydpnaQAQc4+HbrRq7kFRShUKmQpUgZ5SR0PScih7bNcJfT+waUVLwZ4ftoV7UubiYAK1JACSYJjlWZ7eaOlWd3Rm8hDydyU49qhCyM8JWEhXU4M13d6kkLAO5IHvZEn4SMf81LqiQGC4kSNycbvNtOJIA9enalj1MpXw/qBxRy0vyqS4+k5lK1eXaI4UcTmTP+Dp1txIlDsmPdMhM/6gmUEjImRVT1a4Zu3UMqSUgEJUpKihQUIgx7pEHqPyqw6X4Nt2gSH3d6sFQeUME4jaR0HWaeEIz3lyButkSWillJ8yA5ztUtI5ExuRyZ64+tb1SwuFp/ltt7tpJyomYxtXuiJnkTx8aMs9JQCoKJIBwsrWf0/GaYtacWwCiYHTmR8etUh08K4sXUypoS4IC0utkDzL9okD5qEY+s08tGU+z/kqDhJyrHmPZRbSOPnT1LRWDMieJ2mP/E4oO104MqUZlBj39oz3AHFGPTaf0vZh1t7APsX0KlbiQkCVAEkzGJMAx+lJF2V4QQp794R19q2duRgojJ55FWZV7bMlS+VEiYk+nU4xQ9z4lBHlEetGSxrbV/sKjN+AzTLZaGhvKRj7KSn67jJqC41FQVCYiIz37xSheorc5NdoUYzn8qMZ2qjaQ6go8jP9+c6LMVlBh0DrWVXV8X9QfIQP+G3Hx/8AjXSCrolYKT9Z/KqjqPhe/SspdEH0Mg+oPWg9I111agGpcMjCZJkmBx617cq0cNuyHfM4kec8wT09a9NIzPY8Xa8JuH3yaZWvh0J+zNemix7isGnjtQaZ1lHtbHYQdgIBmDwfQ1ZdN0VDyFKWwlsY2kbgkjqfe9KbhbDRBcGQYgCTMYwTml+r60VIKZKAowBxI6x6AVjyZsaT9R0mxRrV82lOxjDbZEJjmJClFUcyoYzzTrRddS8gBRCCAnn4RVE1K8SAlHVe4x28pV8uP7d6lBIIIPIB/CvNbm5a/UtBLg9SadTB2BJHpjPqaSXeiOPLlzbCj7yFFW2J2ylQiIxxzFUqw151BhKiM08t/HRSdqkgnvxRc4y2mmq9CjwtP3QfX9HdZQUpbLgPuhO5QkhU+VXlFIPD6Lvc4lohyVI3KWMtBSRLZUBt9fL1JmrtbeMkLPnGJ+nrTOy1i0SkhGxAJyAAJMckD4DNGOTHuoul8RHjklTR59rLr9u8Wm2C86UhSlF5SQBEEr3eTv1GK70B3enaphaSlPvSFgmeNqFEgnoY6Cr6pdupYX7USOAYMYjnr85osMD7CyMYg+UZmQO9PevihXFor71nKYc2tSRs3qycchvvPQ5rY0Fvd7RSlKWBz0H+kKKqeXWmtugBxKVEAiSO/aPzqG00dLeAuQeZ5+R7elFx97jYCEGj6a23dG5Q4SpKSDuUVCCIKYiRn6VZLezaV/MAyoz5jJ+E9qETooCyr2vlz5dwAyOuKMtbNtsglaQc8HGfTrU4dzVUlaC0vBObdJmADjjFSFtKEJTPmBJx69P87UC7doBMKH1Gfqa43Ng7i58ioQPSBVnk5pL6g0sYtp3dfzoe605pfIyPU0ve1xpOA4APTNLF+Lm0rWAJJAie/FSlmxcNJ/ceOOb4DbzQ0qICpxwU4VHQTFHs6OQMwE9lGSR61Uk+Lngfen/O1Cu+KniZ3Gs8JYo7qLKPBJ+UXJPhm2Cgv2aQrqRIn496kbctmjsG1KQZwRz61QXvEDixlR+fNLby9KgYOar33/5ikFdOvLPTbjU7ROSQesD+8UG94uayEDd8Tj6V5gFqxk1q2fIJzXd3I/Rfsh+xjS9S26l4wfBkKgemKG/jLrqdyiTNV1i6QVoDivtDyxM03YYIEiCCSQE9uxHSpSXq2d3IcKrCQues96LbPQkx2oWyIW4lIUlJUYBPFWJPhl6RBQQeoP5U+PE3vFWTnP1AbaAaZNkEEE8Vp/QnkEQkrEciPxFELtyAmURiFHgg/A0/vQ2kibknwaCPUfWtVz7B0YCAodDvAkfCKym7j/xf3FPHP2NvBGoB0jcEIUY+JCZHqJNfTCClQkZBz9a8r0x11SwhnY1OP5bbaPxCa9Gt/wCUhIJKuJJMknua+gizLN2RrWEqIVJmNsck5n44E1lzAAO72Y6nylXwTnH0NZtBc3HgJJyODMCPkTVY1y6K3AHUBKIVBQok+X7RwIME4z1rNmk4xGjyd6iIyiVIHKgCSc9VfPmq1rNktSy6qNgGxKU8p6KJxztk/wDoVBquqKYSQlaVygqSD03A7Vkd5k5+mKT6t4nCUhLat4SVeXzZPTzHv+FeZpt7IraOnnC5cpQQISw6UESffU2iFTxA3Z64xzTB24AWGwZKUn5Yx+FV69191SFAfy1BO4Y3HAMbROQSMH49opH4e1IkhRMkjJOZJmT86Lwya1em1fcbG1ZbULG5XTj+3HrQ76NypGCCZ7ZrllwTjvP0HWtuXECcEmQT8+lTo0JsHQ8ZUDiK6/eDiKD6kdZJ+RyKlb60rgimthSrxX3qNtteeSkQs46TSMKE1MXABHM0vbj6DaywDxO+f/2H61p7xG8eXCaQpWKxptSjgfhSvGvJyl8Bs9rjpnzmgGdYcKwCs/WpTp7sH+Wok0tNmtCwVNqT8QaMMUKewyyD9N+rqa1+8qPWlW8g0Sl0Yn4UqxRXgRyZIFmea4CCVz9a6S4JqVB60VFI7UzpuuHG+ea0Fc10p70jofyrtgWwdYO4ATgzRDxxJrkHaeOetC35Kk7Qdp79q7nYNmPL7UF7SCTWOvQInil13cg4B5q2PG2CT2LVa2CA2w4pSCtalAgEkgkGCa40ValpcWDBb5zyesetEeBre1KFF8kuAjYIWR8RGOak1PTmi5sSSncfsd+u5QwKXJGNbmJP3arcHb1lHmhlLkpgLzKD3Ap3otzcLj2JX6joPjOKEtLdpqEIa3zznr1hR5oq41Nq3PmdLYI/7bZ3En16CoalaSv5chhKuS4WF5dIWEuiREkmI9CCKLXqalKKAlpc8BSwCflBrz5vUlupUpaihlQ8uckD1ohizcLXtWd0TlRVmPQVWPUzT0q/5/0CUdz0dpxEDc02D1APBrKpNt4mUEAK3KIGTtOayrLro+n2E0MYeHGwgblCFEYntVh0hlShuVO0mRNAabZDcN/br9asjSwcA8dO3yr3SAuuCdiiO5+iVFMfhVQ8WXH8pQSQgdTGU+v+YzVxuf5aiT/21GZ+4s4O7+hXfoZ74rniTRN8oyN2JSYmeh/5rP1EHKNIpBpPcoCWELDThUgrCAlftJO3bJBCTiIiPzpJePwsrQ2lIPu7QkQMyR2kQI6Zq5a/oiQDbvOqVtEpCQCoymAE8ARHXvST/p2zUwEhDjbhkhTipUMxBSkhJGOnevNdKT1fsUTrdIo9866y4pawFJUIEElKQVEgEjiCTz3NDXLoQoLQCQrMdjg57c1adX0VYblgALQk7wlSyFAcqGZ9c0tvNCQUDY6eBvPKSYztzxOPlWmGfHScv2F3u0KWtdWnlODgmeO0fhT+4dWlCFOJUkKhSZBAI7ieaA0Tw0py4abBlKlgqwDgZPORIxXoNzrVs88/p9ync2FAIwQUbUjzIV8egqXUZMaktC25deEVhKXkon75J5kmjXXiGwqMT1GJ4qd/wy5Yu7w6pTZnYtKQfKfvlUhJqxWN82sBOzedip6ghPVSRjnrFRyThs47orqKcLg9Ez8KPsdPU4qSfZpSJUpXAFWBrRXXklQIbA5Q3G4D+1D6neotGm0BtThdO4hXMJ6maWM9a91B1IV3Sxv2WzTi45WpJyf6R0FTJ0u6OSCj4qA/AUSjxA6ELUQBG1LaYPvKzBj0FdOXy3U+R3aogSjEp/09xTOq4B3GB3DbjO0e0UonsTFD/wDUty2edwHRQmhrouTC3Fn/AD0FVW+tylw/zjzIndxVcGKM3uwym/Sy/o1S3uBLrfsln7aO/qKiuLFUgoIWk/aBx/xS1dqWGUKucqXlKR70dCo1GxqRC0iSWz27diKnLG3+kKl6DVNs6DO36EGtb1pOQQPhVkRZM3CAUnaYwpBg/MUl1J5y1MLDpT0UQCk/OpaWwLJYK/egc/aEfjitpekCTMTXKdZQtQ3MBQ78UazqTOdwiTASBNCUWvAe4lsCrvwOcCkmpawCCEmrojSWnhK2VIB4kKBPwSM1MP2e2xSFvN+yT2CiFH5dKph7adyTB3UeXpfdUmRn4U50DQVKV7R8w2ASRParddW9oghi2YSHAklAyokj7x6zTPxJ4cfTbNNtNF1xwBTyhGP6B6T09KtLM5JqCpeovc8MrFrfl7eW/wCVbt43AeZw9h6VMrV1kBplPHYTJ9aef9MuFDaFD2TacqnHxqxvJRb2wXZlgwQFSMkHBJPJNZXGL8cfnzJye5Wri7WbYIfcSmCDIjHcDrXWkXQCkpaRuKhhSgnPbbNdK1JLqiFWDbqjiQnaD6yacMaYpQS2i12gDlJwPQE1JYnJ7Nt/A7T5ZFbrUhxQUj2apg7wnJOfKOI+FGM6i4lSkJ27Afh8alf8IqddS6t1YKY8pIIx60fY6O2ypRWpKp+8ZNXXR57qOy+INcEhQvxKmTLEmekRWU5U1ZzxWqr7F1H+S/PkDuR9C3G2TzABpU+kh6QabPOwPWljqwJJIn/OTXuMyomfIIg5CsEdwaTanqCmIS8nciIC+ZHTePsq9etQueJGyv2TR9q51I91Hz6miUp3e8Znmam3Y6QEGWHvMkhc/ZUc8d6R614aURLe3dBgODcBPY/nTO+8OtHzNLUyr+nKZ9U/pUFtfPsSHQHk905/DkVGeOMuUMvgecXWnXlq4hwI2gEb0glTahPm2nkAjoag1UNtPlKVeRQCscDdnaek16q3rdo7hXkPbp+NQX3hy0fHutr+BhVZ5dMnww6qKt4IS2S4/uEITtwkDzH+rr0qkfvFw8+6ppJWdxyBwNx6nivVh4VCGQ0wCAFbiOe/NJEaK9btpaQ2lCQZUQTuMmTzWGEJxcpKN+F+xSTXABY314ygB633p2kqG5EwPQmDNS/vafZFbDaWN48wlIUIPGcCuddYaddSEuLQIAO4E5+VUrxBoN4w5KErebOQQlRHwIiuh0ut1svrX8g7iSPQdLvwywu4Kkw3A8it5JP3owKsFpqmnX6At5rapMZWmB/4q6j0rxnSfEdzbBaPYeVeFpW2YPTAqG51p1zlwgdExtA9AO1aIdLLG9q/0C7PYl6uhnDVu2pEkymMAGNxEVWdasEBxT6UfzFgkKGUpnptnFVXSX3yCAspB5IwCO1Myhz2eXRvn449KzZFOL3kMmR31wsNFxaFBaSkGATuB645ojwno7Vy57ZbZSloblbsCRwDRTVmtUFTvs0ACZOTHamjOrJEIQmG8hRgmT0JjpSrMktvt/AW62KjrNpf3VwVlG1BMBfISjpArhnQXEn/ALhVtJ5GVfLoKs5s3WzvWt11Sj5dshAH+kYpypDyhsU0tO4YcQBuHoqaMupyJJUq+C/6cmUfS9C1D2m5KQkTyVbBHw616NpC17S3dm3U3EEFU49ZqrI8HXqnfOtbiJmSo5H+nijbTwHcbj5ITPU1oqcqaX0TFpLyFDwjYvLV7G4VA+wiPL8CRxTjTNAsrbKWioj7SjKvqeKk0zwg8gg7giO3507Ghk+879KdYJvwLqor+r+JEsA7UpSo8R5ln9Kp+o6u9cNGHTbqPcbiR+Vemp0G1RlUE9Scn61E6bBHKUH5A03sk3u3/wA+hykkecaK+hpIKZXcpKSFQSVAczHera5qGoXGUMrbMegHymmyfEVq37jYHwSKie8afdTRXRrzN/LYZ5G+ELFeFtQfA9s+ABwB0+MRNMdG8EoZHnXuPMk/lQT3ix1XGPhQLurOq5Uap7Nh8q/3Apy8Fvas7ZkyVz6V1c+JW0iEJmqYncrk0fZ2zf2iT/arY4xgqgqFk2+Q1/V3XTCZHwqW10hasrMfHmi7ZaQPKAB6UWhztVKFOUaY0BxNZUwd+IrVE4rnin9oTFudkKcc6JAI+pOK8+vvFN1eq2btiCcISY/3Hk1lZSSk2GKRdfD1im3R3UeTTVeoGsrKbg4jD6lZ4FRqufu/WsrKARVe27bnvIBPfg/Wlx0zaZbcWgjjMisrKRjHHtL1slSH985M4P6UM94xuUmHRJHeDWVlBKtkc9wm38d920/QUzR48QRBRWqymsFIJR4stVe81PyrZ1ewVksz/wCNZWULBpQY1rlkBAaH+2onNQ08mSwP9tZWV23odRK3r1knhn/61p3xXbgGG/8A61lZRuuDqAUeLkg5T5RwAKmPjlA4brdZSrYZka/H56IihnfHrh4AFZWU2pi0gVzxo8esUK54leV9o/WsrK6w0DL1FxXKvxqBdz3VW6ylOIjfJ9TU1tcFfupgCsrKNHDBCe4NTIWPh2rKyuOJkOCiEuGaysoo5hlvckHmmbFz/wA/rWVlOKFe0/z/AAVlZWVwp//Z", "Vegetarianas"));
        recipesList.add(new Recipes("data:image/jpeg;base64,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", "Massas"));
        recipesList.add(new Recipes("https://www.multireceitas.com.br/wp-content/uploads/2015/04/salada-1-1600x850.jpg", "Saladas"));
        recipesList.add(new Recipes("https://abrilclaudia.files.wordpress.com/2017/03/receita-antepasto-de-berinjela-com-picles.jpg?quality=85&strip=all&strip=info", "Vegetarianas"));
        recipesList.add(new Recipes("https://statig0.akamaized.net/bancodeimagens/35/oz/6c/35oz6cqyqpzz5gwryrvyk9elu.jpg", "Massas"));
        recipesList.add(new Recipes("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEWOpQaGda15mLVpznGuYqfNfPdv0L-P276kVyaWQiXaryJJMg", "Saladas"));
        recipesList.add(new Recipes("https://gds.portal5g-media.com/contentFiles/image/2016/12/FEA/thumbnail/53490_w380h235_1481721200couve-flor-assada.jpg", "Vegetarianas"));
        recipesList.add(new Recipes("https://www.ideiasereceitas.com/wp-content/uploads/2012/03/receita-massa-bacon-cogumelos-ervilhas-750x350.jpg", "Massas"));
        recipesList.add(new Recipes("https://www.tvgazeta.com.br/wp-content/uploads/2015/08/salada.jpg", "Saladas"));
        recipesList.add(new Recipes("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsEPRS53_z73Am8NSXUjKJa3_Z8U4c-mxnvbCwMSmobf25ydqA", "Vegetarianas"));


        return recipesList;
    }

    @Override
    public void onItemClick(Recipes recipes) {
        Toast.makeText(getContext(), "Receita de " + recipes.getRecipes(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(getContext(), MainActivity.class));

    }


}