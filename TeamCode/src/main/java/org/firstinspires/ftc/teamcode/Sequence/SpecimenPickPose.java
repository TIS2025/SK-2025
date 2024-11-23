package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class SpecimenPickPose {
    private List<Action> SpecimenPick = new ArrayList<>();
    public SpecimenPickPose(Intake intake, Outtake outtake, Hang hanger) {
//        SpecimenPick.clear();
//        SpecimenPick.add(
        Actions.runBlocking(

                new SequentialAction(
                        intake.ArmCommands(Intake.ArmState.SAFE),
                        outtake.SpecimenCommands(Outtake.SpecimenState.RELEASE),
                        outtake.BucketCommands(Outtake.BucketState.HALF),
                        outtake.LifterCommands(Outtake.LifterState.SPECIMEN_HEIGHT)

                )
        );
    }
}
