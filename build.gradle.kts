plugins {
    java
    application
}

repositories {
    mavenCentral()
    maven("https://maven.scijava.org/content/repositories/public")
    maven("https://maven.imagej.net/content/repositories/releases")
    maven("https://github.com/axtimwalde/mpicbg")
    maven("https://artifacts.openmicroscopy.org/artifactory/ome.releases")
    maven("https://dev.loci.wisc.edu/maven2/releases")
}

application {
    mainClass.set("net.imagej.launcher.ClassLauncher")
//    val dir = "/~/.local/share/fiji"
    val dir = File(".").absolutePath
    applicationDefaultJvmArgs = listOf("-Dpython.cachedir.skip=true", "-Xmx9918m", /*"-XX:PermSize=128m",*/
                                       /*"-Dimagej.dir=$dir", "-Dij.dir=$dir",*/ "-Dfiji.dir=$dir",
                                       "-Dfiji.defaultLibPath=lib/amd64/server/libjvm.so",
                                       "-Djava.library.path=$dir/lib/linux64:$dir/mm/linux64", "-Dij.debug=true",
                                       "-Dscijava.log.level=debug")
}

dependencies {
    implementation("net.imagej:imagej:2.3.0")
    implementation("net.imagej:imagej-legacy:0.38.0")

    val sceneryVersion = "e2bfbef"
    implementation("graphics.scenery:scenery:$sceneryVersion") { version { strictly(sceneryVersion) } }

    val plugins = listOf(
        "AnalyzeSkeleton_:3.4.2", "Anisotropic_Diffusion_2D:2.0.1", "Archipelago_Plugins:0.5.4", "Arrow_:2.0.2",
        "Auto_Local_Threshold:1.10.1", "Auto_Threshold:1.17.2", "BalloonSegmentation_:3.0.1", "bigwarp_fiji:7.0.2",
        "blockmatching_:2.1.3", "Bug_Submitter:2.1.1", "bUnwarpJ_:2.6.13", "Calculator_Plus:2.0.1", "Cell_Counter:3.0.0",
        "Colocalisation_Analysis:3.0.5", "Color_Inspector_3D:2.5.0", "Colour_Deconvolution:3.0.3",
        "Color_Histogram:2.0.7", "CorrectBleach_:2.0.3", "Correct_3D_Drift:1.0.6", "CPU_Meter:2.0.1",
        "Descriptor_based_registration:2.1.7", "Dichromacy_:2.1.2", "Directionality_:2.3.0", "Feature_Detection:2.0.2",
        "fiji:2.3.1", "Fiji_Archipelago:2.0.1", "Fiji_Developer:2.0.7", "Fiji_Package_Maker:2.1.1", "Fiji_Plugins:3.1.1",
        "FlowJ_:2.0.1", "FS_Align_TrakEM2:2.0.3", "Graph_Cut:1.0.2", "Gray_Morphology:2.3.5", "HDF5_Vibez:1.1.0",
        "Helmholtz_Analysis:2.0.2", "IJ_Robot:2.0.1", "Image_Expression_Parser:3.0.1", "Image_5D:2.0.2",
        "Interactive_3D_Surface_Plot:3.0.0", "IO_:4.1.0", "IsoData_Classifier:2.0.1", "KymographBuilder:2.1.1",
        "Kuwahara_Filter:2.0.1", "Lasso_and_Blow_Tool:2.0.2", "level_sets:1.0.2", "Linear_Kuwahara:2.0.1",
        "LocalThickness_:4.0.3", "LSM_Reader:4.1.2", "LSM_Toolbox:4.1.2", "Manual_Tracking:2.1.1", "MTrack2_:2.0.1",
        "M_I_P:2.0.1", "Multi_Kymograph:3.0.1", "panorama_:3.0.2", "PIV_analyser:1.1.2", "QuickPALM_:1.1.2",
        "RATS_:2.0.2", "Reconstruct_Reader:2.0.4", "register_virtual_stack_slices:3.0.5", "registration_3d:2.0.1",
        "Samples_:2.0.2", "Series_Labeler:2.0.1", "Siox_Segmentation:1.0.5", "Skeletonize3D_:2.1.1",
        "SplineDeformationGenerator_:2.0.2", "SPIM_Registration:5.0.22", "Stack_Manipulation:2.1.2",
        "Statistical_Region_Merging:2.0.1", "Stitching_:3.1.8", "Sync_Win:1.7-fiji4", "Thread_Killer:2.0.1",
        "Time_Lapse:2.1.1", "Time_Stamper:2.1.0", "ToAST_:25.0.2", "TopoJ_:2.0.1", "Trainable_Segmentation:3.2.35",
        "TrakEM2_:1.3.6", "TrakEM2_Archipelago:2.0.3", "trakem2_tps:1.1.4", "Vaa3d_Reader:2.0.3", "Vaa3d_Writer:1.0.3",
        "VIB_:3.0.3", "Video_Editing:2.0.1", "View5D_:2.3.6", "Volume_Calculator:2.0.2", "Volume_Viewer:2.01.2",
        "z_spacing:1.1.1", "3D_Blob_Segmentation:3.0.1", "3D_Objects_Counter:2.0.1")
    for (plugin in plugins)
        implementation("sc.fiji:$plugin")

    implementation("org.janelia.saalfeldlab:n5-viewer_fiji:4.2.1")
    implementation("mpicbg:mpicbg_:1.4.1")
    implementation("ome:bio-formats_plugins:6.7.0")
    implementation("de.biomedical-imaging.imagej:ij_ridge_detect:1.4.1")
    implementation("org.janelia:H5J_Loader_Plugin:1.1.0")

    val jogamp = listOf("gluegen:gluegen-rt", "jogl:jogl-all", "joal:joal", "jocl:jocl")
    for (j in jogamp) {
        implementation("org.jogamp.$j-main:2.3.2")
        //        runtimeOnly("org.jogamp.$j:2.3.2:natives-linux-amd64")
    }
}