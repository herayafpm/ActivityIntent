package com.heraya.activityintent.adapter;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.heraya.activityintent.EditMahasiswa;
import com.heraya.activityintent.MainActivity;
import com.heraya.activityintent.R;
import com.heraya.activityintent.TampilDataMahasiswa;
import com.heraya.activityintent.databaseKampus;
import com.heraya.activityintent.entity.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {
    private databaseKampus db;
    public MahasiswaAdapter(Context context, int resource,List<Mahasiswa> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rows,parent,false);
        }
        final Mahasiswa m = getItem(position);
        TextView tvNama = convertView.findViewById(R.id.tvNama);
        db = Room.databaseBuilder(getContext(),databaseKampus.class,"dbkampus").build();
        tvNama.setText(m.getNama());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                String[] option = {"Edit","Hapus"};
                builder.setTitle("Options "+m.getNama())
                        .setItems(option, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        final Intent intent = new Intent(getContext(), TampilDataMahasiswa.class);
                                        intent.putExtra("nim",m.getNim());
                                        getContext().startActivity(intent);
                                        break;
                                    case 1:
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                                        builder1.setTitle("Konfirmasi")
                                                .setMessage("Apakah anda yakin ingin menghapus "+m.getNama()+" ?")
                                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        deleteMahasiswa(m.getNim());
                                                        notifyDataSetChanged();
                                                        Intent intent1 = new Intent(getContext(),MainActivity.class);
                                                        ((MainActivity)getContext()).finish();
                                                        getContext().startActivity(intent1);

                                                    }
                                                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        AlertDialog dialog1 = builder1.create();
                                        dialog1.show();
                                }
                            }
                        }).setNeutralButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return convertView;
    }
    private void deleteMahasiswa(final String nim){
        new AsyncTask<Void,Void,Integer>(){
            @Override
            protected Integer doInBackground(Void... voids) {
                int status = db.mahasiswaDao().deleteMahasiswa(nim);
                return status;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                Toast.makeText(getContext(),"Data telah terhapus",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
