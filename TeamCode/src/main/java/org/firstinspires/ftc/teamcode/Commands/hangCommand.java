
package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;


public class hangCommand {
    public hangCommand(Hang hanger, Hang.HangState state) {
        Actions.runBlocking(new SequentialAction(
                new InstantAction(() -> hanger.updateHangState(state))
        ));
    }
}
