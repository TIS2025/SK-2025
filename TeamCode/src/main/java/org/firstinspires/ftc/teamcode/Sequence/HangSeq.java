package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class HangSeq {

    private List<Action> Hanglist = new ArrayList<>();
    public HangSeq(Intake intake, Outtake outtake, Hang hanger) {
//        SpecimenHang.clear();
//        SpecimenHang.add(
        Actions.runBlocking(

                new SequentialAction(
                        outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                        intake.ArmCommands(Intake.ArmState.UP),
                        intake.ElbowCommands(Intake.ElbowState.INIT),
                        hanger.hangCommands(Hang.HangState.LOWER_RUNG)

                )
        );
    }
}
