package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Sequence.InitSequence;
import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
@Autonomous
public class StraightTest extends LinearOpMode     {

    private RobotHardware robot;
    private Intake intake;
    private Outtake outtake;
    private Hang hanger;
    private MecanumDrive drive;


    @Override
    public void runOpMode() throws InterruptedException {

        robot = new RobotHardware();
        robot.init(hardwareMap, telemetry);
        intake = new Intake(robot);
        outtake = new Outtake(robot);
        hanger = new Hang(robot);
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(180)));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        new InitSequence(intake,outtake,hanger,1);



        TrajectoryActionBuilder HMAction = drive.actionBuilder(new Pose2d(0, 0, Math.toRadians(180)))



               .strafeToLinearHeading(new Vector2d(-48, 0), Math.toRadians(180));


        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        HMAction.build()
                )
        );


    }
}
