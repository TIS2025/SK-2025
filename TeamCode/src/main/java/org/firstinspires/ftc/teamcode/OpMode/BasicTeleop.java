package org.firstinspires.ftc.teamcode.OpMode;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Sequence.DoDrop;
import org.firstinspires.ftc.teamcode.Sequence.DropHeightSequence;
import org.firstinspires.ftc.teamcode.Sequence.DropSequence;
import org.firstinspires.ftc.teamcode.Sequence.HangDone;
import org.firstinspires.ftc.teamcode.Sequence.HangSeq;
import org.firstinspires.ftc.teamcode.Sequence.InitSequence;
import org.firstinspires.ftc.teamcode.Sequence.PickSeq;
import org.firstinspires.ftc.teamcode.Sequence.PickSequence;
import org.firstinspires.ftc.teamcode.Sequence.PreloadHangSeq;
import org.firstinspires.ftc.teamcode.Sequence.PreloadSpecimenSeq;
import org.firstinspires.ftc.teamcode.Sequence.SpecimenPick;
import org.firstinspires.ftc.teamcode.Sequence.SpecimenPickPose;
import org.firstinspires.ftc.teamcode.Sequence.TransferSequence;
import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Field Oriented Teleop", group="TeleOp")
@Config
public class BasicTeleop extends LinearOpMode
{
    private RobotHardware robot;
    private Intake intake;
    private Outtake outtake;
    private Hang hanger;
    private MecanumDrive mecanumDrive;
    private boolean dpadLeftToggle = false;
    private boolean dpadRightToggle = false;
    private boolean aToggle = false;
    private boolean xToggle = false;
    private boolean downFlag=false;
    private boolean paasingFlag=false;
    private boolean dropFlag=false;
    public static List<Action> runningActions = new ArrayList<>();

    public static int lifterPos=0;
    @Override
    public void runOpMode() {

        robot = new RobotHardware();
        robot.init(hardwareMap, telemetry);
        intake = new Intake(robot);
        outtake = new Outtake(robot);
        hanger = new Hang(robot);
        mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        new InitSequence(intake, outtake, hanger);
        while (opModeInInit())
        {
            if(gamepad1.dpad_up)
            {
                lifterPos+=10;
            }
            else if (gamepad1.dpad_down)
            {
                lifterPos-=10;
            }
            extendTo(lifterPos,1);
        }

        outtake.resetLifter();
        mecanumDrive.lazyImu.get().resetYaw();
        waitForStart();

        while (opModeIsActive()) {

            runningActions = updateAction();
            if(gamepad1.left_stick_button){
                mecanumDrive.lazyImu.get().resetYaw();
            }

            double drive = -gamepad1.left_stick_y*0.8;
            double strafe = -gamepad1.left_stick_x*0.8;
            double turn = -gamepad1.right_stick_x*0.5;

            PoseVelocity2d driveCommand = new PoseVelocity2d(new Vector2d(drive, strafe), turn);

            mecanumDrive.FieldCentricDrive(gamepad1, gamepad1.right_trigger >= 0.5, false);
            if(gamepad1.start){
                mecanumDrive.FieldCentricDrive(gamepad1, true, false);
            }
            if(gamepad1.back){
                mecanumDrive.FieldCentricDrive(gamepad1, false, false);
            }

//            mecanumDrive.setDrivePowers(driveCommand);

            // Todo: Intake sequence
            if (gamepad1.a && !downFlag) {
                new PickSequence(intake, outtake, hanger);
                mecanumDrive.FieldCentricDrive(gamepad1, true, false);
            }
            //Todo: Bucket drop
            if (gamepad1.b)
            {
                new DoDrop(intake,outtake,hanger);
                downFlag=false;

            }
            if
            (gamepad1.x && !downFlag) {
                new PickSeq(intake,outtake,hanger);
                mecanumDrive.FieldCentricDrive(gamepad1,true, false);
            }

            //ToDo: Reverse
            if(gamepad2.x){
                new PickSeq(intake);
            }


            // Todo: Transfer sequence
            if (gamepad1.y && !downFlag ) {
                new TransferSequence(intake, outtake, hanger);


            }

            // Todo: Drop Height adjustment
            if (gamepad1.left_bumper) {
                new DropHeightSequence(intake, outtake, hanger);
                downFlag=true;
                mecanumDrive.FieldCentricDrive(gamepad1,true, false);

            }

            // Todo: Drop sequence
            if (gamepad1.right_bumper) {
                downFlag=false;
                new DropSequence(intake, outtake, hanger);

            }


            if (gamepad2.b) {
                new InitSequence(intake, outtake, hanger);
                downFlag=false;
                paasingFlag=false;
                dropFlag=false;
                runningActions.clear();
            }

            // Todo: Lifter control
            if (gamepad2.dpad_up) {
                robot.lifter.setTargetPosition(robot.lifter.getCurrentPosition() + 50);
                robot.lifter.setPower(1.0);
            }

            if (gamepad2.dpad_down) {
                robot.lifter.setTargetPosition(robot.lifter.getCurrentPosition() - 50);
                robot.lifter.setPower(1.0);
            }

            // Todo: Yaw Arm control
            if (gamepad1.dpad_left && !dpadLeftToggle) {
                robot.yawArm.setPosition(robot.yawArm.getPosition() + 0.1);
                dpadLeftToggle = true;
            } else if (!gamepad1.dpad_left) {
                dpadLeftToggle = false;
            }

            if (gamepad1.dpad_right && !dpadRightToggle) {
                robot.yawArm.setPosition(robot.yawArm.getPosition() - 0.1);
                dpadRightToggle = true;
            } else if (!gamepad1.dpad_right) {
                dpadRightToggle = false;
            }

            //todo: Elbow Extend

            if(gamepad2.a){
                robot.elbow1.setPosition(0.42);
                robot.elbow2.setPosition(0.42);
            }



            if(gamepad2.y){
                robot.elbow1.setPosition(0.8);
                robot.elbow2.setPosition(0.8);
            }

            //todo: Specimen

//            // Todo: Specimen Release
//            if (gamepad2.x) {
//                new SpecimenPickPose(intake, outtake, hanger);
//            }
//            // Todo: Pick
//            if (gamepad2.b) {
//                new SpecimenPick(intake, outtake, hanger);
//            }
//            if(gamepad2.left_bumper){
//                new PreloadSpecimenSeq(intake,outtake,hanger);
//            }
//            if(gamepad2.right_bumper){
//                new PreloadHangSeq(intake,outtake,hanger);
//            }


            // Todo: Specimen Release
            if (gamepad1.left_trigger>0.5) {
                new SpecimenPickPose(intake, outtake, hanger);
            }
            // Todo: Pick
            if (gamepad1.right_trigger>0.5) {
                runningActions.add(SpecimenPick.SpecimenPickAction(intake,outtake,hanger));
            }
            if(gamepad2.left_bumper){
                new PreloadSpecimenSeq(intake,outtake,hanger);
            }
            if(gamepad2.right_bumper){
                new PreloadHangSeq(intake,outtake,hanger);
            }




            //Todo: Hanging
            if(gamepad2.left_trigger>0.5){
                new HangSeq(intake,outtake,hanger);
            }
            if(gamepad2.right_trigger>0.5){
                new HangDone(intake,outtake,hanger);
            }
            //Todo: Hanger
            if (gamepad2.dpad_left ) {
                robot.leftHang.setTargetPosition(robot.leftHang.getCurrentPosition() + 100);
                robot.leftHang.setPower(1.0);
            }
            if (gamepad2.dpad_right ) {
                robot.leftHang.setTargetPosition(robot.leftHang.getCurrentPosition() - 100);
                robot.leftHang.setPower(1.0);

            }

            // Todo: Telemetry for all states
            telemetry.addData("Intake Motor Power", robot.intakeMotor.getPower());
            telemetry.addData("Yaw Arm Position", robot.yawArm.getPosition());
            telemetry.addData("Arm Position", robot.arm.getPosition());
            telemetry.addData("Specimen Position", robot.specimen.getPosition());
            telemetry.addData("Elbow Position", robot.elbow1.getPosition());
            telemetry.addData("Elbow Position", robot.elbow2.getPosition());
            telemetry.addData("Lifter Position", robot.lifter.getCurrentPosition());
            telemetry.addData("Bucket Position", robot.bucket.getPosition());
            telemetry.addData("Left Hang Position", robot.leftHang.getCurrentPosition());
            telemetry.addData("LeftFront Motor Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("LeftRear Motor Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("RightFront Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("RightRear Motor Current", mecanumDrive.leftFront.getCurrent(CurrentUnit.AMPS));


            telemetry.addData("Dekho Ye Current:",robot.lifter.getCurrent(CurrentUnit.AMPS));
            telemetry.update();
        }
    }

    public static List<Action> updateAction(){
        TelemetryPacket packet = new TelemetryPacket();
        List<Action> newActions = new ArrayList<>();
        List<Action> RemovableActions = new ArrayList<>();

        for (Action action : runningActions) {
//            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
//        runningActions.removeAll(RemovableActions);
        return newActions;
    }
    public void extendTo(int TargetPos,double pow){
        robot.lifter.setTargetPosition(TargetPos);
        robot.lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifter.setPower(pow);
    }


}
