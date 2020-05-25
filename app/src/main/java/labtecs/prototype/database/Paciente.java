package labtecs.prototype.database;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "paciente")
public class Paciente implements Parcelable {


    protected Paciente(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        cpf = in.readString();
        nome = in.readString();
        byte tmpSenteDor = in.readByte();
        senteDor = tmpSenteDor == 0 ? null : tmpSenteDor == 1;
        if (in.readByte() == 0) {
            dorIntensidade = null;
        } else {
            dorIntensidade = in.readInt();
        }
        byte tmpDesconforto = in.readByte();
        desconforto = tmpDesconforto == 0 ? null : tmpDesconforto == 1;
        desconfortoLocal = in.readString();
        byte tmpTeveCirurgia = in.readByte();
        teveCirurgia = tmpTeveCirurgia == 0 ? null : tmpTeveCirurgia == 1;
        quandoCirurgia = in.readString();
        tipoCirurgia = in.readString();
        byte tmpDorPosCirurgia = in.readByte();
        dorPosCirurgia = tmpDorPosCirurgia == 0 ? null : tmpDorPosCirurgia == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(cpf);
        dest.writeString(nome);
        dest.writeByte((byte) (senteDor == null ? 0 : senteDor ? 1 : 2));
        if (dorIntensidade == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dorIntensidade);
        }
        dest.writeByte((byte) (desconforto == null ? 0 : desconforto ? 1 : 2));
        dest.writeString(desconfortoLocal);
        dest.writeByte((byte) (teveCirurgia == null ? 0 : teveCirurgia ? 1 : 2));
        dest.writeString(quandoCirurgia);
        dest.writeString(tipoCirurgia);
        dest.writeByte((byte) (dorPosCirurgia == null ? 0 : dorPosCirurgia ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getTipoCirurgia() {
        return tipoCirurgia;
    }

    public void setTipoCirurgia(String tipoCirurgia) {
        this.tipoCirurgia = tipoCirurgia;
    }

    public Paciente(String cpf, String nome, Boolean senteDor, Integer dorIntensidade, Boolean desconforto, String desconfortoLocal, Boolean teveCirurgia, String quandoCirurgia, String tipoCirurgia, Boolean dorPosCirurgia) {
        this.cpf = cpf;
        this.nome = nome;
        this.senteDor = senteDor;
        this.dorIntensidade = dorIntensidade;
        this.desconforto = desconforto;
        this.desconfortoLocal = desconfortoLocal;
        this.teveCirurgia = teveCirurgia;
        this.quandoCirurgia = quandoCirurgia;
        this.tipoCirurgia = tipoCirurgia;
        this.dorPosCirurgia = dorPosCirurgia;

        if(cpf == "") cpf = "Sem CPF";
        if(nome == "") nome = "Sem Nome";

    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private Integer id;



    private String cpf = "";
    private String nome = "";

    private Boolean senteDor = false;
    private Integer dorIntensidade = 1;

    private Boolean desconforto = false;
    private String desconfortoLocal = "";

    private Boolean teveCirurgia = false;
    private String quandoCirurgia = "";
    private String tipoCirurgia = "Nenhuma";

    private Boolean dorPosCirurgia = false;

    public Boolean getSenteDor() {
        return senteDor;
    }

    public void setSenteDor(Boolean senteDor) {
        this.senteDor = senteDor;
    }

    public Integer getDorIntensidade() {
        return dorIntensidade;
    }

    public void setDorIntensidade(Integer dorIntensidade) {
        this.dorIntensidade = dorIntensidade;
    }

    public Boolean getDesconforto() {
        return desconforto;
    }

    public void setDesconforto(Boolean desconforto) {
        this.desconforto = desconforto;
    }

    public String getDesconfortoLocal() {
        return desconfortoLocal;
    }

    public void setDesconfortoLocal(String desconfortoLocal) {
        this.desconfortoLocal = desconfortoLocal;
    }

    public Boolean getTeveCirurgia() {
        return teveCirurgia;
    }

    public void setTeveCirurgia(Boolean teveCirurgia) {
        this.teveCirurgia = teveCirurgia;
    }

    public String getQuandoCirurgia() {
        return quandoCirurgia;
    }

    public void setQuandoCirurgia(String quandoCirurgia) {
        this.quandoCirurgia = quandoCirurgia;
    }

    public Boolean getDorPosCirurgia() {
        return dorPosCirurgia;
    }

    public void setDorPosCirurgia(Boolean dorPosCirurgia) {
        this.dorPosCirurgia = dorPosCirurgia;
    }

    public java.lang.String getCpf() {
        return cpf;
    }

    public void setCpf(java.lang.String cpf) {
        this.cpf = cpf;
    }

    public java.lang.String getNome() {
        return nome;
    }

    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


}
