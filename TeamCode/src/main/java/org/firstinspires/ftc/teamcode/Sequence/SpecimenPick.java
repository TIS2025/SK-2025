package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class SpecimenPick {
    private List<Action> SpecimenPick = new ArrayList<>();

    public SpecimenPick(Intake intake, Outtake outtake, Hang hanger) {
//        SpecimenPick.clear();
//        SpecimenPick.add(
        Actions.runBlocking(

                new SequentialAction(
                        outtake.SpecimenCommands(Outtake.SpecimenState.RELEASE),
                        outtake.BucketCommands((Outtake.BucketState.HALF)),
                        outtake.LifterCommands(Outtake.LifterState.SPECIMEN_HEIGHT),
                        new SleepAction(0.5),
                        outtake.SpecimenCommands(Outtake.SpecimenState.GRIP),
                        new SleepAction(0.2),
                        new ParallelAction(
                                outtake.LifterCommands(Outtake.LifterState.SPECIMEN_OUT_HEIGHT)
                        )

                )
        );
    }


    public static Action SpecimenPickAction(Intake intake, Outtake outtake, Hang hanger) {
        return new SequentialAction(
                outtake.SpecimenCommands(Outtake.SpecimenState.RELEASE),
                outtake.BucketCommands((Outtake.BucketState.HALF)),
                outtake.LifterCommands(Outtake.LifterState.SPECIMEN_HEIGHT),
                new SleepAction(0.5),
                outtake.SpecimenCommands(Outtake.SpecimenState.GRIP),
                new SleepAction(0.2),
                new ParallelAction(
                        outtake.LifterCommands(Outtake.LifterState.SPECIMEN_OUT_HEIGHT)
                )

        );
    }
}
