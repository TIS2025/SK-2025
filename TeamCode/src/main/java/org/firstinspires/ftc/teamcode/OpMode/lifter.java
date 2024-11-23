package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name=" WHO ")
@Config
public class lifter extends LinearOpMode {

    DcMotorEx m;
    public static int finalpos=0;
    public int pos=0;
    @Override
    public void runOpMode() throws InterruptedException {
        m=hardwareMap.get(DcMotorImplEx.class,"m");
        m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive())
        {
            if(gamepad1.x)
            {
                m.setPower(1);
            }
            else
            {
                m.setPower(0);
            }
            if(gamepad1.dpad_up)
            {
                pos=pos+10;
                lifter(m,pos);
            }
            else if(gamepad1.dpad_down)
            {
                pos=pos-10;
                lifter(m,pos);
            }

            telemetry.addData("Lifter Current: ",m.getCurrent(CurrentUnit.AMPS));
            telemetry.update();
        }


    }
    public void lifter(DcMotorEx motor,int pos)
    {
        motor.setTargetPosition(pos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(0.8);
    }
}
