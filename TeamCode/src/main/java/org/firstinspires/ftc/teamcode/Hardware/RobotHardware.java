package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.GuardedBy;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;

public class RobotHardware {

    // Intake Actuators
    public DcMotorEx intakeMotor = null;
    public Servo yawArm = null;
    public Servo arm = null;
    public Servo specimen = null;
    public Servo elbow1 = null;
    public Servo elbow2 = null;
    public Servo sliderlock = null;

    // Outtake Actuators
    public DcMotorEx lifter = null;
    public DcMotorEx lifter2 = null;
    public Servo bucket = null;

    // Hang Actuators
    public DcMotorEx leftHang = null;


    // Sensor
    public RevColorSensorV3 colorSensor = null;

    // Drivetrain Motors and Mecanum Drive
    public DcMotorEx dtFrontLeftMotor = null;
    public DcMotorEx dtFrontRightMotor = null;
    public DcMotorEx dtBackLeftMotor = null;
    public DcMotorEx dtBackRightMotor = null;
    public MecanumDrive drivetrain = null;

    // IMU setup with thread safety
    private final Object imuLock = new Object();
    @GuardedBy("imuLock")
    public IMU imu;
    private Thread imuThread;
    private double imuAngle = 0;
    private double imuOffset = 0;
    private double startOffset = 0;

    // Battery Voltage
    private ElapsedTime voltageTimer = new ElapsedTime();
    private double voltage = 12.0;

    // Singleton pattern for RobotHardware
    private static RobotHardware instance = null;
    public boolean enabled = false;  // To track if the hardware is enabled

    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }
        instance.enabled = true;
        return instance;
    }

    // Hardware Map
    private HardwareMap hardwareMap;

    // Init method to map hardware components
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;

        // Map Intake Actuators
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        yawArm = hardwareMap.get(Servo.class, "yawArm");
        arm = hardwareMap.get(Servo.class, "arm");
        elbow1 = hardwareMap.get(Servo.class, "elbow1");
        elbow2 = hardwareMap.get(Servo.class, "elbow2");
        //sliderlock = hardwareMap.get(Servo.class, "sliderlock");

        specimen = hardwareMap.get(Servo.class, "specimen");

        // Map Outtake Actuators
        lifter = hardwareMap.get(DcMotorEx.class, "lifter1");
        lifter2 = hardwareMap.get(DcMotorEx.class, "lifter2");

        bucket = hardwareMap.get(Servo.class, "bucket");

        // Map Hang Actuators
        leftHang = hardwareMap.get(DcMotorEx.class, "leftHang");

        // Map Color Sensor
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "color");
        colorSensor.setGain(50);
        // Configure Lifter Motor
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Configure Lifter Motor
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifter.setDirection(DcMotorSimple.Direction.REVERSE);

        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        lifter.setDirection(DcMotorSimple.Direction.REVERSE);

        // Configure Hang Motor
        leftHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


//        leftHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftHang.setDirection(DcMotorSimple.Direction.REVERSE);

        // Map Drivetrain Motors and configure them
        if (Globals.IS_CUSTOMDRIVE) {
            drivetrain = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

            dtFrontLeftMotor = hardwareMap.get(DcMotorEx.class, "leftFront");
            dtBackLeftMotor = hardwareMap.get(DcMotorEx.class, "leftBack");
            dtFrontRightMotor = hardwareMap.get(DcMotorEx.class, "rightFront");
            dtBackRightMotor = hardwareMap.get(DcMotorEx.class, "rightBack");

            dtFrontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            dtBackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            dtFrontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            dtBackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            dtFrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            dtBackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            dtFrontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            dtBackRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        // Map IMU
        if (Globals.IS_IMU) {
            synchronized (imuLock) {
                imu = hardwareMap.get(IMU.class, "imu");
                IMU.Parameters parameters = new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                        )
                );
                imu.initialize(parameters);
            }
        }

        // Map Voltage Sensor
        VoltageSensor voltageSensor = hardwareMap.voltageSensor.iterator().next();
        voltage = voltageSensor.getVoltage();
    }

    // Start IMU thread to constantly update IMU readings
    public void startIMUThread(LinearOpMode opMode) {
        imuThread = new Thread(() -> {
            while (!opMode.isStopRequested()) {
                synchronized (imuLock) {
                    imuAngle = AngleUnit.normalizeRadians(
                            imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) + startOffset
                    );
                }
            }
        });
        imuThread.start();
    }

    // Get current angle from the IMU
    public double getAngle() {
        return AngleUnit.normalizeRadians(imuAngle - imuOffset);
    }

    // Reset the IMU angle offset
    public void resetIMU() {
        imuOffset = imuAngle;
    }

    // Get current voltage
    public double getVoltage() {
        return voltage;
    }
}
