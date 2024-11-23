package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class PreloadSpecimenSeq {
    private List<Action> SpecimenHang = new ArrayList<>();
    public PreloadSpecimenSeq(Intake intake, Outtake outtake, Hang hanger) {
//        SpecimenHang.clear();
//        SpecimenHang.add(
        Actions.runBlocking(

                new SequentialAction(
                        outtake.BucketCommands(Outtake.BucketState.INIT),
                        outtake.LifterCommands(Outtake.LifterState.HIGH_CHAMBER_HEIGHT1),
                        intake.ArmCommands(Intake.ArmState.SAFE)

                )
        );
    }
}
