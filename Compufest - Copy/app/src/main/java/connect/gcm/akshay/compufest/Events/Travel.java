package connect.gcm.akshay.compufest.Events;

import android.os.Parcel;
import android.os.Parcelable;


public class Travel implements Parcelable {
    String id;
    String name;
    int image;





    public Travel(String id,String name, int image) {
        this.name = name;
        this.image = image;
        this.id=id;
    }

    protected Travel(Parcel in) {
        id=in.readString();
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(image);
    }
}


