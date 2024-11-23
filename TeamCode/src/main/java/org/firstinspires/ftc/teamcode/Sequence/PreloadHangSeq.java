package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class PreloadHangSeq {
    private List<Action> SpecimenHang = new ArrayList<>();
    public PreloadHangSeq(Intake intake, Outtake outtake, Hang hanger) {
//        SpecimenHang.clear();
//        SpecimenHang.add(
        Actions.runBlocking(

                new SequentialAction(
                        outtake.LifterCommands(Outtake.LifterState.HIGH_CHAMBER_HEIGHT1),
                        outtake.BucketCommands(Outtake.BucketState.HALF),

                                outtake.BucketCommands(Outtake.BucketState.SPECIMEN_OUTAKE),
                                outtake.LifterCommands(Outtake.LifterState.HIGH_CHAMBER_HEIGHT2),


                        new SleepAction(0.4),
                        outtake.SpecimenCommands(Outtake.SpecimenState.RELEASE),
                        intake.ArmCommands(Intake.ArmState.SAFE),
                        outtake.BucketCommands(Outtake.BucketState.INIT),
                        outtake.LifterCommands(Outtake.LifterState.SPECIMEN_HEIGHT)


                )
        );
    }
}
