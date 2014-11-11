package pl.mg6.simplegithubclient.data;

import com.google.gson.annotations.SerializedName;

import hrisey.Parcelable;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor(suppressConstructorProperties = true)
@Parcelable @Value public class Age implements android.os.Parcelable {

    @SerializedName("calculatedAge")
    Integer calculatedAge;

    @SerializedName("userAge")
    int userAge;

    public Integer getNetAge() {
        if (calculatedAge == null)
            return null;
        else
            return calculatedAge - userAge;
    }

}